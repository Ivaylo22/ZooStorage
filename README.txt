Curstom operation explanation:

This feature optimizes item extraction from multiple storage locations based on their proximity to a given city. Here's how it works:

-Each storage has a city field added to the database.
-Specify a city when extracting items and provide input quantity.
-The system sorts storage locations by proximity to the city.
-Begin extraction from the closest storage, allocating the input quantity.
-If the quantity is insufficient, additional items are allocated from farther storages.