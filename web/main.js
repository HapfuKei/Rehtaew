lastID = "trash";
var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
var shorDays = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
var celsiusSymbol=" &#8451";
function clickOnPlitsToLock(id) {
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
    var currentDay=getDataTime(object.list[i].dt).getDay();
    if (currentDay == prevDay) {
      if (object.list[i].main.temp_max > maxTemp) { maxTemp = object.list[i].main.temp_max; }
      if (object.list[i].main.temp_min < minTemp) { minTemp = object.list[i].main.temp_min; }
    }
    if((currentDay!=prevDay)||(i==39)) {
      document.getElementById("plitsTemperatureMax" + plitsItter).innerHTML += maxTemp+celsiusSymbol;
      document.getElementById("plitsTemperatureMin" + plitsItter).innerHTML += minTemp+celsiusSymbol;
      plitsItter++;
      maxTemp = -999;
      minTemp = 999;
    }
    prevDay = currentDay;
  }
}

function updateData() {
  document.getElementById("tempratureMain").innerHTML += object.list[0].main.temp; //temperatura sejchas7io
}
