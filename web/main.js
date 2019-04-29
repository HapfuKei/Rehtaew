lastID = "trash";
var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
var shorDays = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
var celsiusSymbol = " &#8451";
var degreeSymbol = " &#176";
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

  if (id == "plits7") {
    document
      .getElementById("informationalPanel")
      .classList.remove("informationalPanelDown");
    document
      .getElementById("informationalPanel")
      .classList.toggle("informationalPanelRightUp");
  } else {
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

function typeWriter(id,text) {
  var i=0;
  console.log(text);
  document.getElementById(id).innerHTML = "";
  if (i < text.length) {
    document.getElementById(id).innerHTML += text.charAt(i);
    i++;
    setTimeout(typeWriter, 50);
  }
}

function setupTimePlits(startIndex) {
  document.getElementById("nameOfDay").innerHTML = days[getDataTime(object.list[startIndex].dt).getDay()];
  idItter = 1;
  for (i = startIndex; i < 8 + startIndex; i++) {
    //setup data
    //typeWriter("timeCell" + (idItter) + "Time",(getDataTime(object.list[i].dt).getHours() + ":00"));
   // document.getElementById("timeCell" + (idItter) + "Time").innerHTML = getDataTime(object.list[i].dt).getHours() + ":00";
    document.getElementById("timeCell" + (idItter) + "statusImg").src = "http://openweathermap.org/img/w/" + object.list[i].weather[0].icon + ".png";
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
}

function onLoadPage() {
  //set names of days
  document.getElementById("plitsDayName1").innerHTML += shorDays[getDataTime(object.list[0].dt).getDay()];
  document.getElementById("plitsDayName2").innerHTML += shorDays[getDataTime(object.list[8].dt).getDay()];
  document.getElementById("plitsDayName3").innerHTML += shorDays[getDataTime(object.list[16].dt).getDay()];
  document.getElementById("plitsDayName4").innerHTML += shorDays[getDataTime(object.list[24].dt).getDay()];
  document.getElementById("plitsDayName5").innerHTML += shorDays[getDataTime(object.list[32].dt).getDay()];

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
      document.getElementById("plitsTemperatureMax" + plitsItter).innerHTML += maxTemp + celsiusSymbol;
      document.getElementById("plitsTemperatureMin" + plitsItter).innerHTML += minTemp + celsiusSymbol;
      plitsItter++;
      maxTemp = -999;
      minTemp = 999;
    }
    prevDay = currentDay;
  }

  //setup time cells time value
  setupTimePlits(0);

}

function updateData() {
  document.getElementById("tempratureMain").innerHTML += object.list[0].main.temp; //temperatura sejchas7io
}
