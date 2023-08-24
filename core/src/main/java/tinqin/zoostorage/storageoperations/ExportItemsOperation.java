package tinqin.zoostorage.storageoperations;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.mutable.MutableInt;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
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
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExportItemsOperation implements ExportItems {
    private final StorageRepository storageRepository;
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

        Double[] cityCordinates = fetchCoordinates(apikey, input.getCity());
        Double cityLat = cityCordinates[0];
        Double cityLng = cityCordinates[1];
        MutableInt inputQuantity = new MutableInt(input.getQuantity());

        List<Storage> storages = storageRepository.findAllByItemId(UUID.fromString(input.getItemId()));
        sortStorageByDistanceAscending(storages, cityLat, cityLng, apikey);

        Double finalPrice = storages.stream()
                .takeWhile(storage -> inputQuantity.getValue() > 0)
                .mapToDouble(storage -> {
                    Integer quantityToProcess = Math.min(inputQuantity.getValue(), storage.getQuantity());
                    Double price = quantityToProcess * storage.getPrice();

                    storage.setQuantity(storage.getQuantity() - quantityToProcess);
                    inputQuantity.setValue(inputQuantity.getValue() - quantityToProcess);
                    storageRepository.save(storage);

                    return price;
                }).sum();

        return ExportResponse.builder()
                .finalPrice(finalPrice)
                .itemId(input.getItemId())
                .build();
    }

    private void sortStorageByDistanceAscending(List<Storage> storageList, Double cityLat, Double cityLng, String apikey) {
        storageList.sort(Comparator.comparingDouble(storage -> {
            try {
                Double[] storageCoordinates = fetchCoordinates(apikey, storage.getCity());
                Double storageLat = storageCoordinates[0];
                Double storageLng = storageCoordinates[1];
                return findDistanceByCoordinates(cityLat, cityLng, storageLat, storageLng);
            } catch (Exception e) {
                return Double.MAX_VALUE;
            }
        }));
    }

    private Double[] fetchCoordinates(String apiKey, String location) throws Exception {
        String apiUrl = "https://www.mapquestapi.com/geocoding/v1/address"
                + "?key=" + apiKey
                + "&location=" + location;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        Integer responseCode = connection.getResponseCode();

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
        Double lat = firstLocation.getJSONObject("latLng").getDouble("lat");
        Double lng = firstLocation.getJSONObject("latLng").getDouble("lng");

        return new Double[]{lat, lng};
    }

    private Double findDistanceByCoordinates(Double lat1, Double lng1, Double lat2, Double lng2) {
        Double radius = 6371.0;

        Double lat1Rad = Math.toRadians(lat1);
        Double lon1Rad = Math.toRadians(lng1);
        Double lat2Rad = Math.toRadians(lat2);
        Double lon2Rad = Math.toRadians(lng2);

        Double dlat = lat2Rad - lat1Rad;
        Double dlon = lon2Rad - lon1Rad;

        Double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(dlon / 2), 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return radius * c;
    }
}
