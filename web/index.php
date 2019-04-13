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
            <span id="tempratureMain">20*C</span>
            <span id="mainDescription">Clear</span>
        </div>
        <div></div>
        <div class="container">
            <div class="row plits">
                <!-- main plist-->
                <div class="col">
                    <div onclick="clickOnPlitsToLock(this.id)" id="plits1">
                        <div>Mon</div>
                        <div id="plitsWheaterStatus1">
                            <img src="../web/images/statusIcons/sunCloud.ico" alt="">
                        </div>
                        <div id="plitsTemperatureDay">20</div>
                        <div id="plitsTemperatureNIght">7</div>
                    </div>
                </div>
                <div class="col">
                    <div onclick="clickOnPlitsToLock(this.id)" id="plits2">
                        <div>Tue</div>
                        <div id="plitsWheaterStatus2">
                            <img src="../web/images/statusIcons/sunCloud.ico" alt="">
                        </div>
                        <div id="plitsTemperatureDay">20</div>
                        <div id="plitsTemperatureNIght">7</div>
                    </div>
                </div>
                <div class="col">
                    <div onclick="clickOnPlitsToLock(this.id)" id="plits3">
                        <div>Wed</div>
                        <div id="plitsWheaterStatus3">
                            <img src="../web/images/statusIcons/sunCloud.ico" alt="">
                        </div>
                        <div id="plitsTemperatureDay">20</div>
                        <div id="plitsTemperatureNIght">7</div>
                    </div>
                </div>
                <div class="col">
                    <div onclick="clickOnPlitsToLock(this.id)" id="plits">
                        <div>Thu</div>
                        <div id="plitsWheaterStatus4">
                            <img src="../web/images/statusIcons/sunCloud.ico" alt="">
                        </div>
                        <div id="plitsTemperatureDay">20</div>
                        <div id="plitsTemperatureNIght">7</div>
                    </div>
                </div>
                <div class="col">
                    <div onclick="clickOnPlitsToLock(this.id)" id="plits5">
                        <div>Fri</div>
                        <div id="plitsWheaterStatus5">
                            <img src="../web/images/statusIcons/sunCloud.ico" alt="">
                        </div>
                        <div id="plitsTemperatureDay">20</div>
                        <div id="plitsTemperatureNIght">7</div>
                    </div>
                </div>
                <div class="col">
                    <div onclick="clickOnPlitsToLock(this.id)" id="plits6">
                        <div>Sat</div>
                        <div id="plitsWheaterStatus6">
                            <img src="../web/images/statusIcons/sunCloud.ico" alt="">
                        </div>
                        <div id="plitsTemperatureDay">20</div>
                        <div id="plitsTemperatureNIght">7</div>
                    </div>
                </div>
                <div class="col">
                    <div onclick="clickOnPlitsToLock(this.id)" id="plits7">
                        <div>Sun</div>
                        <div id="plitsWheaterStatus7">
                            <img src="../web/images/statusIcons/sunCloud.ico" alt="">
                        </div>
                        <div id="plitsTemperatureDay">20</div>
                        <div id="plitsTemperatureNIght">7</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container   informationalPanel" id="informationalPanel">
            <div class="row">
                <!--informationalPanel-->
                <div class="col-md-3 col-lg-3">
                    dadsa
                </div>
                <div class="col-md-9 col-lg-9 timeCells row">
                    <div id="timeCell1" class="col">
                        <div>00:00</div>
                        <div><img src="../web/images/statusIcons/cloud.ico" alt=""></div>
                        <div>temp=1</div>
                    </div>
                    <div id="timeCell2" class="col">
                        <div>02:00</div>
                        <div><img src="../web/images/statusIcons/cloud.ico" alt=""></div>
                        <div>temp=1</div>
                    </div>
                    <div id="timeCell3" class="col">
                        <div>04:00</div>
                        <div><img src="../web/images/statusIcons/cloud.ico" alt=""></div>
                        <div>temp=1</div>
                    </div>
                    <div id="timeCell4" class="col">
                        <div>05:00</div>
                        <div><img src="../web/images/statusIcons/cloud.ico" alt=""></div>
                        <div>temp=1</div>
                    </div>
                    <div id="timeCell5" class="col">
                        <div>08:00</div>
                        <div><img src="../web/images/statusIcons/cloud.ico" alt=""></div>
                        <div>temp=1</div>
                    </div>
                    <div id="timeCell6" class="col">
                        <div>10:00</div>
                        <div><img src="../web/images/statusIcons/cloud.ico" alt=""></div>
                        <div>temp=1</div>
                    </div>
                </div>
            </div>
            <div class="row">
                <!--informationalPanel-->
                <div class="col-md-3 col-lg-3">
                    <div style="font-size:75%">Temperature</div>
                    <div style="font-size:75%">Wind</div>
                    <div style="font-size:75%">Precipitation</div>
                </div>
                <div class="col-md-9 col-lg-9 timeCells row">
                    <div id="timeCell1" class="col">
                        <div>00:00</div>
                        <div>data</div>
                        <div>temp=1</div>
                    </div>
                    <div id="timeCell2" class="col">
                        <div>02:00</div>
                        <div></div>
                        <div>temp=1</div>
                    </div>
                    <div id="timeCell3" class="col">
                        <div>04:00</div>
                        <div></div>
                        <div>temp=1</div>
                    </div>
                    <div id="timeCell4" class="col">
                        <div>05:00</div>
                        <div></div>
                        <div>temp=1</div>
                    </div>
                    <div id="timeCell5" class="col">
                        <div>08:00</div>
                        <div></div>
                        <div>temp=1</div>
                    </div>
                    <div id="timeCell6" class="col">
                        <div>10:00</div>
                        <div></div>
                        <div>temp=1</div>
                    </div>
                </div>
            </div>
        </div>
        <div>460413</div>

        <?php
        $url = "http://api.openweathermap.org/data/2.5/forecast?q=Daugavpils,lv&mode=xml&appid=105aa35c4d473a05d3297b2d972dc497";
        $xml = simplexml_load_file($url);
        print($xml);
        ?>
</body>

</html>