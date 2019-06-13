

lastID = "trash";
var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
var shorDays = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
var celsiusSymbol = " &#8451";
var degreeSymbol = " &#176";

function getDataFormServer(cityName) {
  $.ajax({
    url: "wheatherHook.php",
    type: "POST",
    async: false,
    dataType: "json",
    data: { cityName: cityName },
    success: function (result) {
      console.log("transmission succes");
      object = result;
    },
    error: function (log) {
      console.log("transmission failed:" + log);
      alert("Get data form server error");
    }
  });
}

function clickOnPlitsToLock(id) {

  var plitNimber = id.substring(5, 6) - 1;
  setupTimePlits(plitNimber * 8)

  document.getElementById(lastID).classList.remove("plitsLock");
  document.getElementById(id).classList.toggle("plitsLock");
  lastID = id;

  if (id == "plits1") {
    document
      .getElementById("informationalPanel")
      .classList.remove("informationalPanelDown");
    document
      .getElementById("informationalPanel")
      .classList.toggle("informationalPanelLeftUp");
  } else {
    document
      .getElementById("informationalPanel")
      .classList.remove("informationalPanelLeftUp");
    document
      .getElementById("informationalPanel")
      .classList.add("informationalPanelDown");
  }

  if (id == "plits5") {
    console.log("up");
    document
      .getElementById("informationalPanel")
      .classList.remove("informationalPanelDown");
    document
      .getElementById("informationalPanel")
      .classList.toggle("informationalPanelRightUp");
  } else {
    console.log("down");
    document
      .getElementById("informationalPanel")
      .classList.remove("informationalPanelRightUp");
    document
      .getElementById("informationalPanel")
      .classList.add("informationalPanelDown");
  }
}

function getDataTime(unixTimeStamp) {
  return new Date(unixTimeStamp * 1000);
}

function getWindDerrection(degrees) {
  if ((degrees > 0) && (degrees < 22.5)) { return "N" };
  if ((degrees > 22.5) && (degrees < 45)) { return "NNE" };
  if ((degrees > 45) && (degrees < 67.5)) { return "NE" };
  if ((degrees > 67.5) && (degrees < 90)) { return "ENE" };
  if ((degrees > 90) && (degrees < 112.5)) { return "E" };
  if ((degrees > 112.5) && (degrees < 135)) { return "ESE" };
  if ((degrees > 135) && (degrees < 157.5)) { return "SE" };
  if ((degrees > 157.5) && (degrees < 180)) { return "SSE" };
  if ((degrees > 180) && (degrees < 202.5)) { return "S" };
  if ((degrees > 202.5) && (degrees < 225)) { return "SSW" };
  if ((degrees > 225) && (degrees < 247.5)) { return "SW" };
  if ((degrees > 247.5) && (degrees < 270)) { return "WSW" };
  if ((degrees > 270) && (degrees < 292.5)) { return "W" };
  if ((degrees > 292.5) && (degrees < 315)) { return "WNW" };
  if ((degrees > 315) && (degrees < 337.5)) { return "NW" };
  if ((degrees > 337.5)) { return "NNW" };
}

function timePlitsAnnimationFadeOutAdd(startIndex) {
  document.getElementById("nameOfDay").classList.add("m-fadeOut");
  idItter = 1;
  for (i = startIndex; i < 8 + startIndex; i++) {
    document.getElementById("timeCell" + (idItter) + "Time").classList.add("m-fadeOut");
    document.getElementById("timeCell" + (idItter) + "statusImg").classList.add("m-fadeOut");
    document.getElementById("timeCell" + (idItter) + "Temperature").classList.add("m-fadeOut");
    document.getElementById("timeCell" + (idItter) + "WindDerrection").classList.add("m-fadeOut");
    document.getElementById("timeCell" + (idItter) + "Precipitation").classList.add("m-fadeOut");
    idItter++;
  }
}

function timePlitsAnnimationFadeOutRemove(startIndex) {
  document.getElementById("nameOfDay").classList.remove("m-fadeOut");
  idItter = 1;
  for (i = startIndex; i < 8 + startIndex; i++) {
    document.getElementById("timeCell" + (idItter) + "Time").classList.remove("m-fadeOut");
    document.getElementById("timeCell" + (idItter) + "statusImg").classList.remove("m-fadeOut");
    document.getElementById("timeCell" + (idItter) + "Temperature").classList.remove("m-fadeOut");
    document.getElementById("timeCell" + (idItter) + "WindDerrection").classList.remove("m-fadeOut");
    document.getElementById("timeCell" + (idItter) + "Precipitation").classList.remove("m-fadeOut");
    idItter++;
  }
}

function timePlitsAnnimationFadeInAdd(startIndex) {
  document.getElementById("nameOfDay").classList.add("m-fadeIn");
  idItter = 1;
  for (i = startIndex; i < 8 + startIndex; i++) {
    document.getElementById("timeCell" + (idItter) + "Time").classList.add("m-fadeIn");
    document.getElementById("timeCell" + (idItter) + "statusImg").classList.add("m-fadeIn");
    document.getElementById("timeCell" + (idItter) + "Temperature").classList.add("m-fadeIn");
    document.getElementById("timeCell" + (idItter) + "WindDerrection").classList.add("m-fadeIn");
    document.getElementById("timeCell" + (idItter) + "Precipitation").classList.add("m-fadeIn");
    idItter++;
  }
}

function timePlitsAnnimationFadeInRemove(startIndex) {
  document.getElementById("nameOfDay").classList.remove("m-fadeIn");
  idItter = 1;
  for (i = startIndex; i < 8 + startIndex; i++) {
    document.getElementById("timeCell" + (idItter) + "Time").classList.remove("m-fadeIn");
    document.getElementById("timeCell" + (idItter) + "statusImg").classList.remove("m-fadeIn");
    document.getElementById("timeCell" + (idItter) + "Temperature").classList.remove("m-fadeIn");
    document.getElementById("timeCell" + (idItter) + "WindDerrection").classList.remove("m-fadeIn");
    document.getElementById("timeCell" + (idItter) + "Precipitation").classList.remove("m-fadeIn");
    idItter++;
  }
}

function setupTimePlits(startIndex) {
  timePlitsAnnimationFadeInRemove(startIndex);
  timePlitsAnnimationFadeOutAdd(startIndex);

  document.getElementById("nameOfDay").innerHTML = days[getDataTime(object.list[startIndex].dt).getDay()];

  idItter = 1;
  for (i = startIndex; i < 8 + startIndex; i++) {
    //setup data
    document.getElementById("timeCell" + (idItter) + "Time").innerHTML = getDataTime(object.list[i].dt).getHours() + ":00";
    document.getElementById("timeCell" + (idItter) + "statusImg").src = "../web/images/statusIcons/" + object.list[i].weather[0].icon + ".png";
    document.getElementById("timeCell" + (idItter) + "Temperature").innerHTML = object.list[i].main.temp + degreeSymbol;
    document.getElementById("timeCell" + (idItter) + "WindDerrection").innerHTML = getWindDerrection(object.list[i].wind.deg);
    if (object.list[i].rain !== undefined) {
      document.getElementById("timeCell" + (idItter) + "Precipitation").innerHTML = object.list[i].rain["3h"];
    } else if (object.list[i].cloud !== undefined) {
      document.getElementById("timeCell" + (idItter) + "Precipitation").innerHTML = object.list[i].snow["3h"];
    } else {
      document.getElementById("timeCell" + (idItter) + "Precipitation").innerHTML = "none";
    }
    idItter++;
  }
  setTimeout(function () {
    timePlitsAnnimationFadeOutRemove(startIndex);
    timePlitsAnnimationFadeInAdd(startIndex);
  }, 300);

}

function onLoadPage(cityName) {
  getDataFormServer(cityName);
  //set names of days
  document.getElementById("plitsDayName1").innerHTML = shorDays[getDataTime(object.list[0].dt).getDay()];
  document.getElementById("plitsDayName2").innerHTML = shorDays[getDataTime(object.list[8].dt).getDay()];
  document.getElementById("plitsDayName3").innerHTML = shorDays[getDataTime(object.list[16].dt).getDay()];
  document.getElementById("plitsDayName4").innerHTML = shorDays[getDataTime(object.list[24].dt).getDay()];
  document.getElementById("plitsDayName5").innerHTML = shorDays[getDataTime(object.list[32].dt).getDay()];

  //set icons by hightnoone
  plitsItter = 1;
  for (i = 0; i < 40; i++) {
    if (getDataTime(object.list[i].dt).getHours() == 12) {
      document.getElementById("plitsWheaterStatus" + plitsItter).src = "http://openweathermap.org/img/w/" + object.list[i].weather[0].icon + ".png";
      plitsItter++;
    }
  }

  //set max day temp and min
  var prevDay = getDataTime(object.list[0].dt).getDay();
  var plitsItter = 1;
  maxTemp = -999;
  minTemp = 999;

  for (i = 0; i < 40; i++) {
    var currentDay = getDataTime(object.list[i].dt).getDay();
    if ((currentDay == prevDay) && (plitsItter != 6)) {
      if (object.list[i].main.temp_max > maxTemp) { maxTemp = object.list[i].main.temp_max; }
      if (object.list[i].main.temp_min < minTemp) { minTemp = object.list[i].main.temp_min; }
    }
    if ((currentDay != prevDay) || (i == 39) && (plitsItter != 6)) { //fix
      document.getElementById("plitsTemperatureMax" + plitsItter).innerHTML = maxTemp + celsiusSymbol;
      document.getElementById("plitsTemperatureMin" + plitsItter).innerHTML = minTemp + celsiusSymbol;
      plitsItter++;
      maxTemp = -999;
      minTemp = 999;
    }
    prevDay = currentDay;
  }

  //setup time cells time value
  setupTimePlits(0);
  idItter = 1;
  setDefaultDemostrationsValue();
}

function openTab(evt, TabName) {
  // Declare all variables
  var i, tabcontent, tablinks;

  // Get all elements with class="tabcontent" and hide them
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  // Get all elements with class="tablinks" and remove the class "active"
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace("active", "");
  }

  // Show the current tab, and add an "active" class to the button that opened the tab
  document.getElementById(TabName).style.display = "block";
  evt.currentTarget.className += "active";
}

// ---- ML functions

function setDefaultDemostrationsValue() {
  document.getElementById("Year").value=2019;
  document.getElementById("Month").value =05;
  document.getElementById("Day").value =22;
  document.getElementById("Hour").value =22;
  document.getElementById("Temperature").value =20.78;
  document.getElementById("Relative_Humidity").value =69.00;
  document.getElementById("Sea_Level_Pressure").value =1007.70;
  document.getElementById("Total_Precipitation_high").value =0.00;
  document.getElementById("Total_Precipitation_low").value =0.00;
  document.getElementById("Snowfall_Amount_high").value =0.00;
  document.getElementById("Snowfall_Amount_low").value =0.00;
  document.getElementById("Total_Cloud_Cover").value =0.00;
  document.getElementById("High_Cloud_Cover").value =0.00;
  document.getElementById("Medium_Cloud_Cover").value =0.00;
  document.getElementById("Low_Cloud_Cover").value =0.00;
  document.getElementById("Sunshine_Duration").value =49.72;
  document.getElementById("Shortwave_Radiation").value =17.80;
  document.getElementById("Wind_Speed_10m").value =3.22;
  document.getElementById("Wind_Direction_10m").value =333.43;
  document.getElementById("Wind_Speed_80m").value =5.32;
  document.getElementById("Wind_Direction_80m").value =28.30;
  document.getElementById("Wind_Speed_900mb").value =4.33;
  document.getElementById("Wind_Direction_900mb").value =85.24;
  document.getElementById("Wind_Gust").value =5.04;

}

function makeJsonDataObjectForModel() {
  jsonObject = {
    "Month": document.getElementById("Year").value,
    "Year": document.getElementById("Month").value,
    "Hour": document.getElementById("Day").value,
    "Day": document.getElementById("Hour").value,
    "Temperature": document.getElementById("Temperature").value,
    "Relative_Humidity": document.getElementById("Relative_Humidity").value,
    "Sea_Level_Pressure": document.getElementById("Sea_Level_Pressure").value,
    "Total_Precipitation_high": document.getElementById("Total_Precipitation_high").value,
    "Total_Precipitation_low": document.getElementById("Total_Precipitation_low").value,
    "Snowfall_Amount_high": document.getElementById("Snowfall_Amount_high").value,
    "Snowfall_Amount_low": document.getElementById("Snowfall_Amount_low").value,
    "Total_Cloud_Cover": document.getElementById("Total_Cloud_Cover").value,
    "High_Cloud_Cover": document.getElementById("High_Cloud_Cover").value,
    "Medium_Cloud_Cover": document.getElementById("Medium_Cloud_Cover").value,
    "Low_Cloud_Cover": document.getElementById("Low_Cloud_Cover").value,
    "Sunshine_Duration": document.getElementById("Sunshine_Duration").value,
    "Shortwave_Radiation": document.getElementById("Shortwave_Radiation").value,
    "Wind_Speed_10m": document.getElementById("Wind_Speed_10m").value,
    "Wind_Direction_10m": document.getElementById("Wind_Direction_10m").value,
    "Wind_Speed_80m": document.getElementById("Wind_Speed_80m").value,
    "Wind_Direction_80m": document.getElementById("Wind_Direction_80m").value,
    "Wind_Speed_900mb": document.getElementById("Wind_Speed_900mb").value,
    "Wind_Direction_900mb": document.getElementById("Wind_Direction_900mb").value,
    "Wind_Gust": document.getElementById("Wind_Gust").value
  }
  return jsonObject;
}


function mlAnswerRequest(modelData) {
  $.ajax({
    url: "http://localhost:5000/api",
    type: "POST",
    async: false,
    crossDomain: true,
    "Access-Control-Allow-Origin": "*",
    crossOrigin: true,
    data: { testData: JSON.stringify(modelData) },
    success: function (result) {
      alert("result " + result);
    },
    error: function (response) {
      alert("error-" + response.status + ": " + response.responseText);
    }
  });
}

function sendModelRequest() {
  inputModelData = makeJsonDataObjectForModel();
  mlAnswerRequest(inputModelData)
}