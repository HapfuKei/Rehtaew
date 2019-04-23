lastID="trash";
function clickOnPlitsToLock(id) {
    document.getElementById(lastID).classList.remove('plitsLock');
    document.getElementById(id).classList.toggle('plitsLock')
    lastID=id;

    if(id=="plits1"){
      document.getElementById('informationalPanel').classList.remove('informationalPanelDown');
      document.getElementById('informationalPanel').classList.toggle('informationalPanelLeftUp');
    }
    else
    {
      document.getElementById('informationalPanel').classList.remove('informationalPanelLeftUp');
      document.getElementById('informationalPanel').classList.add('informationalPanelDown');
    }
    
    if(id=="plits7"){
      document.getElementById('informationalPanel').classList.remove('informationalPanelDown');
      document.getElementById('informationalPanel').classList.toggle('informationalPanelRightUp');
    }
    else
    {
      document.getElementById('informationalPanel').classList.remove('informationalPanelRightUp');
      document.getElementById('informationalPanel').classList.add('informationalPanelDown');
    }
  }

  function updateData(){
    document.getElementById("tempratureMain").innerHTML+=object.list[39].main.temp; //temperatura sejchas
    
  }