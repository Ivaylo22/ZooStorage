package tinqin.zoostorage.storageoperations;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.exceptions.BadRequestException;
import tinqin.zoostorage.exceptions.NotEnoughQuantityException;
import tinqin.zoostorage.model.exportitems.ExportRequest;
import tinqin.zoostorage.model.exportitems.ExportResponse;
import tinqin.zoostorage.model.exportitems.ExportItems;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.model.getinfobyid.GetInfoById;
import tinqin.zoostorage.model.getinfobyid.GetInfoByIdRequest;
import tinqin.zoostorage.model.getinfobyid.GetInfoByIdResponse;
import tinqin.zoostorage.repository.StorageRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExportItemsOperation implements ExportItems {
    private final StorageRepository storageRepository;
    private final ModelMapper modelMapper;
    private final GetInfoById getInfoById;

    @Value("${location.apikey}")
    private String apikey;

    @Override
    public ExportResponse process(ExportRequest input) throws Exception {
        GetInfoByIdRequest getInfoRequest = GetInfoByIdRequest
                .builder()
                .itemId(UUID.fromString(input.getItemId()))
                .build();
        GetInfoByIdResponse fullInfo = getInfoById.process(getInfoRequest);

        if(fullInfo.getTotalQuantity() < input.getQuantity()) {
            throw new NotEnoughQuantityException(fullInfo.getTitle());
        }

        double[] cityCordinates = fetchCoordinates(apikey, input.getCity());
        double cityLat = cityCordinates[0];
        double cityLng = cityCordinates[1];
        Integer inputQuantity = input.getQuantity();
        Double finalPrice = 0.0;

        List<Storage> storages = storageRepository.findAllByItemId(UUID.fromString(input.getItemId()));

        sortStorageByProximity(storages, cityLat, cityLng, apikey);

        for (Storage storage: storages) {
            if (inputQuantity >= storage.getQuantity()) {
                finalPrice += storage.getQuantity() * storage.getPrice();
                inputQuantity -= storage.getQuantity();
                storage.setQuantity(0);
                storageRepository.save(storage);
            } else {
                finalPrice += storage.getQuantity() * storage.getPrice();
                storage.setQuantity(storage.getQuantity() - inputQuantity);
                storageRepository.save(storage);
                break;
            }
        }

        return ExportResponse.builder()
                .finalPrice(finalPrice)
                .itemId(input.getItemId())
                .build();
    }

    private void sortStorageByProximity(List<Storage> storageList, double cityLat, double cityLng, String apikey) {
        storageList.sort(Comparator.comparingDouble(storage -> {
            try {
                double[] storageCoordinates = fetchCoordinates(apikey, storage.getCity());
                double storageLat = storageCoordinates[0];
                double storageLng = storageCoordinates[1];
                return haversineDistance(cityLat, cityLng, storageLat, storageLng);
            } catch (Exception e) {
                return Double.MAX_VALUE;
            }
        }));
    }

    private double[] fetchCoordinates(String apiKey, String location) throws Exception {
        String apiUrl = "https://www.mapquestapi.com/geocoding/v1/address"
                + "?key=" + apiKey
                + "&location=" + location;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if(responseCode != HttpURLConnection.HTTP_OK) {
            throw new BadRequestException();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONObject responseJson = new JSONObject(response.toString());
        JSONArray locations = responseJson.getJSONArray("results")
                .getJSONObject(0)
                .getJSONArray("locations");

        JSONObject firstLocation = locations.getJSONObject(0);
        double lat = firstLocation.getJSONObject("latLng").getDouble("lat");
        double lng = firstLocation.getJSONObject("latLng").getDouble("lng");

        return new double[]{lat, lng};
    }

    private double haversineDistance(double lat1, double lng1, double lat2, double lng2) {
        double radius = 6371.0;

        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lng1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lng2);

        double dlat = lat2Rad - lat1Rad;
        double dlon = lon2Rad - lon1Rad;

        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return radius * c;
    }
}
