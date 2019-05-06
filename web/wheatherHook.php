<?php
$apiKey = "105aa35c4d473a05d3297b2d972dc497";
$cityName = $_POST["cityName"];
$googleApiUrl = "http://api.openweathermap.org/data/2.5/forecast?q=" . $cityName . "&lang=en&units=metric&appid=" . $apiKey;

        $ch = curl_init();

        curl_setopt($ch, CURLOPT_HEADER, 0);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($ch, CURLOPT_URL, $googleApiUrl);
        curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
        curl_setopt($ch, CURLOPT_VERBOSE, 0);
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
        $response = curl_exec($ch);
        echo $response;
        curl_close($ch);    
?>