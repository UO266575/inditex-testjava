const baseUrl = "http://localhost:8080/api/v1/prices";
const headers = { "Content-Type": "application/json" };

pm.test("Test 1: petición a las 10:00 del dí­a 14 del producto 35455   para la brand 1 (ZARA)", function () {
    pm.sendRequest({
        url: baseUrl + "?applicationDate=2020-06-14T10%3A00%3A00&productId=35455&brandId=1",
        method: "GET",
        header: headers,
    }, function (err, response) {
        pm.expect(response.code).to.eql(200);
        pm.expect(response.json()).to.eql({
            "productId": 35455,
            "brandId": 1,
            "priceList": 1,
            "startDate": "2020-06-14T00:00:00",
            "endDate": "2020-12-31T23:59:59",
            "price": 35.50,
            "currency": "EUR"
        });
    });
});

pm.test("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)", function () {
    pm.sendRequest({
        url: baseUrl + "?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1",
        method: "GET",
        header: headers,
    }, function (err, response) {
        pm.expect(response.code).to.eql(200);
        pm.expect(response.json()).to.eql({
            "brandId": 1,
            "startDate": "2020-06-14T15:00:00",
            "endDate": "2020-06-14T18:30:00",
            "priceList": 2,
            "productId": 35455,
            "price": 25.45,
            "currency": "EUR"
        });
    });
});

pm.test("Test 3: petición a las 21:00 del dí­a 14 del producto 35455   para la brand 1 (ZARA)", function () {
    pm.sendRequest({
        url: baseUrl + "?applicationDate=2020-06-14T21:00:00&productId=35455&brandId=1",
        method: "GET",
        header: headers,
    }, function (err, response) {
        pm.expect(response.code).to.eql(200);
        pm.expect(response.json()).to.eql({
            "brandId": 1,
            "startDate": "2020-06-14T00:00:00",
            "endDate": "2020-12-31T23:59:59",
            "priceList": 1,
            "productId": 35455,
            "price": 35.50,
            "currency": "EUR",
        });
    });
});

pm.test("Test 4: petición a las 10:00 del dÃía 15 del producto 35455   para la brand 1 (ZARA)", function () {
    pm.sendRequest({
        url: baseUrl + "?applicationDate=2020-06-15T10:00:00&productId=35455&brandId=1",
        method: "GET",
        header: headers,
    }, function (err, response) {
        pm.expect(response.code).to.eql(200);
        pm.expect(response.json()).to.eql({
            "brandId": 1,
            "startDate": "2020-06-15T00:00:00",
            "endDate": "2020-06-15T11:00:00",
            "priceList": 3,
            "productId": 35455,
            "price": 30.50,
            "currency": "EUR"
        });
    });
});

pm.test("Test 5: petición a las 21:00 del dí­a 16 del producto 35455   para la brand 1 (ZARA)", function () {
    pm.sendRequest({
        url: baseUrl + "?applicationDate=2020-06-16T21:00:00&productId=35455&brandId=1",
        method: "GET",
        header: headers,
    }, function (err, response) {
        pm.expect(response.code).to.eql(200);
        pm.expect(response.json()).to.eql({
            "brandId": 1,
            "startDate": "2020-06-15T16:00:00",
            "endDate": "2020-12-31T23:59:59",
            "priceList": 4,
            "productId": 35455,
            "price": 38.95,
            "currency": "EUR",
        });
    });
});