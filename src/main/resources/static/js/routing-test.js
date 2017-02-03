var testApp = angular.module("testApp", ["ngRoute"]);

console.log("testApp module created");

testApp.config(function($routeProvider) {
    console.log("Initializing ng-router");
    $routeProvider
        .when("/", {
//            template: "<h2>test 1</h2>"
            templateUrl: "test1.html",
            controller: "controller1"

        })
        .when("/test2", {
//            template: "<h2>test 2</h2>"
            templateUrl: "test2.html",
            controller: "controller2"

        })
        .when("/test3", {
//            template: "<h2>test 3</h2>"
            templateUrl: "test3.html",
            controller: "controller3"

        })
});

testApp.controller('controller1', function($scope, $location, $rootScope) {
    console.log("init controller 1...")

    $scope.getAttendees = function(targetEventName) {
        console.log("in getAttendees() for " + targetEventName);
        $location.path("/test2");
        console.log("done in getAttendees()");
    }
//    $rootScope.targetEventName = targetEventName

$rootScope.getAttendees = $scope.getAttendees
});

testApp.controller('controller2', function($scope, $rootScope) {
    console.log("init controller 2...")
    $scope.testGlobalValue = "Hello value";


    $rootScope.testGlobalValue = $scope.testGlobalValue;
});

testApp.controller('controller3', function($scope) {
    console.log("init controller 3...")
});


testApp.controller('testController', function($scope, $http) {
    console.log("Initializing testController");
    $scope.testVar = "Testing Angular JS Routing";

    $scope.getPeople = function() {
        console.log("fetching person from list")

        $http.get("/test_person.json")
            .then(
                function successCallback(response) {
                    console.log(response.data);
                    console.log("Adding data to scope");
                    $scope.testItemList = response.data;
                },
                function errorCallback(response) {
                    console.log("Unable to get data");
                });
    };

//    $scope.getEvent = function() {
        //    console.log("fetching events")
        //
        //    $http.get("/get_events.json")
        //            .then(
        //                function successCallback(response) {
        //                    console.log(response.data);
        //                    console.log("Adding data to scope");
        //                    $scope.eventList = response.data;
        //                },
        //                function errorCallback(response) {
        //                    console.log("Unable to get data");
        //                });
        //
        //    };

        $scope.eventList = [
                             {
                               "myEventId": 284,
                               "eventName": "Iron Pints",
                               "guests": [
                                 {
                                   "guestId": 287,
                                   "firstName": "Maurice",
                                   "contactRequests": [
                                     {
                                       "myRequestId": 291,
                                       "requesteeEmailAddress": "music@gmail.com",
                                       "requesterEmailAddress": "mauricet1520@gmail.com",
                                       "requestStatus": "pending",
                                       "time": null
                                     },
                                     {
                                       "myRequestId": 290,
                                       "requesteeEmailAddress": "music@gmail.com",
                                       "requesterEmailAddress": "mauricet1520@gmail.com",
                                       "requestStatus": "pending",
                                       "time": null
                                     }
                                   ],
                                   "lastName": "Thomas",
                                   "company": "Iron Yard",
                                   "position": "student",
                                   "email": "mauricet1520@gmail.com",
                                   "password": "password",
                                   "image": null,
                                   "showImage": false
                                 }
                               ],
                               "location": "Iron Yard",
                               "address": "Atlanta",
                               "time": 1485583500000
                             },
                             {
                               "myEventId": 285,
                               "eventName": "Java Crash Course",
                               "guests": [

                               ],
                               "location": "Iron Yard",
                               "address": "Atlanta",
                               "time": 1485695100000
                             },
                             {
                               "myEventId": 286,
                               "eventName": "PTA meeting",
                               "guests": [

                               ],
                               "location": "Dacula High School",
                               "address": "Dacula",
                               "time": 1490007900000
                             },
                             {
                               "myEventId": 289,
                               "eventName": "Hackathon",
                               "guests": [

                               ],
                               "location": "The Omni",
                               "address": "222 Bell Dr. Atlanta, Ga",
                               "time": null
                             }
                           ]



    $scope.getPeople();
//    $scope.getEvent();




//   $scope.testItemList = [
//                           {
//                             "firstName": "Tom",
//                             "lastName": "York",
//                             "profession": "student"
//                           },
//                           {
//                             "firstName": "Maurice",
//                             "lastName": "Thomas",
//                             "profession": "student"
//                           }
//                         ]
});



