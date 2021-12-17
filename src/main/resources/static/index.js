angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1';

//    $scope.loadProducts = function () {
//        $http.get(contextPath + '/products')
//            .then(function (response) {
//                $scope.ProductList = response.data;
//            });
//    };

        $scope.loadProducts = function (pageIndex = 1) {
            $http({
            url: contextPath + '/students',
            method: 'GET',
            params: {
            title_part: $scope.filter ? &scope.filter.title_part : null,
            min_price: $scope.filter ? &scope.filter.min_price : null,
            max_price: $scope.filter ? &scope.filter.max_price : null
            }
            }).then(function(response) {
                $scope.ProductsList = response.data.content;
                });
        };


//    $scope.changePrice = function (productId, delta) {
//        $http({
//            url: contextPath + '/products/change_price',
//            method: 'GET',
//            params: {
//                productId: productId,
//                delta: delta
//            }
//        }).then(function (response) {
//            $scope.loadProducts();
//        });
//    }

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

//        $scope.filterByPriceBetween = function () {
//        console.log($scope.filter);
//            $http({
//                    url: contextPath + '/products/filter',
//                    method: 'GET',
//                    params: {
//                        min: $scope.filter.min,
//                        max: $scope.filter.max
//                    }
//                }).then(function (response) {
//                    $scope.ProductList = response.data;
//            });
//    }


    $scope.loadProducts();
});