<!DOCTYPE html>



<html>

<head>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>


    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Rehtaew today!</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="main.css">
    <script src="main.js"></script>

</head>

<body> 

<?php
$apiKey = "105aa35c4d473a05d3297b2d972dc497";
$cityId = "Daugavpils,lv";
$googleApiUrl = "http://api.openweathermap.org/data/2.5/forecast?q=" . $cityId . "&lang=en&units=metric&appid=" . $apiKey;//."&callback=obj"

$ch = curl_init();

curl_setopt($ch, CURLOPT_HEADER, 0);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($ch, CURLOPT_URL, $googleApiUrl);
curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
curl_setopt($ch, CURLOPT_VERBOSE, 0);
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
$response = curl_exec($ch);

curl_close($ch);
$data = json_decode($response);
?>
<script>
var object = JSON.parse('<?php echo $response ?>');
document.addEventListener("DOMContentLoaded", function() {
    onLoadPage();
});

</script>

    <div id="trash"></div>
    <header></header>
    <!--site header -->
    <div class="container">
        <div class="searchTable">
            <!-- select city-->
            <input type="text" id="cityInputBox">
            <span class="glyphicon glyphicon-search"></span>
        </div>
        <div class="row">
            <!-- Main temprature -->
            <span id="tempratureMain"></span>
            <span id="mainDescription">Clear</span>
        </div>
        <div></div>
        <div class="container">
            <div class="row plits">
                <!-- main plist-->
                <div class="col">
                    <div onclick="clickOnPlitsToLock(this.id)" id="plits1">
                        <div id="plitsDayName1"></div>
                        <div >
                            <img id="plitsWheaterStatus1" src="../web/images/statusIcons/sunCloud.ico" alt="">
                        </div>
                        <div id="plitsTemperatureMax1"></div>
                        <div id="plitsTemperatureMin1"></div>
                    </div>
                </div>
                <div class="col">
                    <div onclick="clickOnPlitsToLock(this.id)" id="plits2">
                        <div id="plitsDayName2"></div>
                        <div >
                            <img id="plitsWheaterStatus2" src="../web/images/statusIcons/sunCloud.ico" alt="">
                        </div>
                        <div id="plitsTemperatureMax2"></div>
                        <div id="plitsTemperatureMin2"></div>
                    </div>
                </div>
                <div class="col">
                    <div onclick="clickOnPlitsToLock(this.id)" id="plits3">
                        <div id="plitsDayName3"></div>
                        <div >
                            <img id="plitsWheaterStatus3" src="../web/images/statusIcons/sunCloud.ico" alt="">
                        </div>
                        <div id="plitsTemperatureMax3"></div>
                        <div id="plitsTemperatureMin3"></div>
                    </div>
                </div>
                <div class="col">
                    <div onclick="clickOnPlitsToLock(this.id)" id="plits4">
                        <div id="plitsDayName4"></div>
                        <div >
                            <img id="plitsWheaterStatus4" src="../web/images/statusIcons/sunCloud.ico" alt="">
                        </div>
                        <div id="plitsTemperatureMax4"></div>
                        <div id="plitsTemperatureMin4"></div>
                    </div>
                </div>
                <div class="col">
                    <div onclick="clickOnPlitsToLock(this.id)" id="plits5">
                        <div id="plitsDayName5"></div>
                        <div >
                            <img id="plitsWheaterStatus5" src="../web/images/statusIcons/sunCloud.ico" alt="">
                        </div>
                        <div id="plitsTemperatureMax5"></div>
                        <div id="plitsTemperatureMin5"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container   informationalPanel" id="informationalPanel">
            <div class="row">
                <!--informationalPanel-->
                <div class="col-md-2 col-lg-2" id="nameOfDay"></div>
                <div class="col-md-10 col-lg-10 timeCells row">
                    <div class="col">
                        <div id="timeCell1Time"></div>
                        <div><img id="timeCell1statusImg" src="" alt=""></div>
                    </div>
                    <div class="col">
                        <div id="timeCell2Time"></div>
                        <div><img id="timeCell2statusImg" src="" alt=""></div>
                    </div>
                    <div class="col">
                        <div id="timeCell3Time"></div>
                        <div><img id="timeCell3statusImg" src="" alt=""></div>
                    </div>
                    <div class="col">
                        <div id="timeCell4Time"></div>
                        <div><img id="timeCell4statusImg" src="" alt=""></div>
                    </div>
                    <div class="col">
                        <div id="timeCell5Time"></div>
                        <div><img id="timeCell5statusImg" src="" alt=""></div>
                    </div>
                    <div class="col">
                        <div id="timeCell6Time"></div>
                        <div><img id="timeCell6statusImg" src="" alt=""></div>
                    </div>
                    <div class="col">
                        <div id="timeCell7Time"></div>
                        <div><img id="timeCell7statusImg" src="" alt=""></div>
                    </div>
                    <div class="col">
                        <div id="timeCell8Time"></div>
                        <div><img id="timeCell8statusImg" src="" alt=""></div>
                    </div>
                </div>
            </div>
            <div class="row">
                <!--informationalPanel-->
                <div class="col-md-2 col-lg-2">
                    <div style="font-size:75%">Temperature</div>
                    <div style="font-size:75%">Wind</div>
                    <div style="font-size:75%">Precipitation</div>
                </div>
                <div class="col-md-10 col-lg-10 timeCells row">
                    <div class="col">
                        <div id="timeCell1Temperature" ></div>
                        <div id="timeCell1WindDerrection"></div>
                        <div id="timeCell1Precipitation"></div>
                    </div>
                    <div class="col">
                        <div id="timeCell2Temperature"></div>
                        <div id="timeCell2WindDerrection"></div>
                        <div id="timeCell2Precipitation"></div>
                    </div>
                    <div class="col">
                        <div id="timeCell3Temperature"></div>
                        <div id="timeCell3WindDerrection"></div>
                        <div id="timeCell3Precipitation"></div>
                    </div>
                    <div class="col">
                        <div id="timeCell4Temperature"></div>
                        <div id="timeCell4WindDerrection"></div>
                        <div id="timeCell4Precipitation"></div>
                    </div>
                    <div class="col">
                        <div id="timeCell5Temperature"></div>
                        <div id="timeCell5WindDerrection"></div>
                        <div id="timeCell5Precipitation"></div>
                    </div>
                    <div class="col">
                        <div id="timeCell6Temperature"></div>
                        <div id="timeCell6WindDerrection"></div>
                        <div id="timeCell6Precipitation"></div>
                    </div>
                    <div class="col">
                        <div id="timeCell7Temperature"></div>
                        <div id="timeCell7WindDerrection"></div>
                        <div id="timeCell7Precipitation"></div>
                    </div>
                    <div class="col">
                        <div id="timeCell8Temperature"></div>
                        <div id="timeCell8WindDerrection"></div>
                        <div id="timeCell8Precipitation"></div>
                    </div>
                </div>
            </div>
        </div>
</body>

</html> 