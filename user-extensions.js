Selenium.prototype.doShowTitleCard = function(title) {
  var body_el = this.browserbot.getDocument().getElementsByTagName('body')[0];

  if (this.browserbot.getDocument().getElementById('light') == null) {
    var light_div = this.browserbot.getDocument().createElement('div');
    light_div.setAttribute('id', 'light');
    light_div.setAttribute('style', 'display: none; position: absolute; top: 25%; left: 25%; width: 50%; height: 50%; padding: 16px; border: 12px double white; background-color: black; z-index:1002; overflow: auto; color: white;');
    light_div.innerHTML = title;
    body_el.appendChild(light_div);
  }

  if (this.browserbot.getDocument().getElementById('fade') == null) {  
    var fade_div = this.browserbot.getDocument().createElement('div');
    fade_div.setAttribute('id', 'fade');
    fade_div.setAttribute('style', 'display: none; position: absolute; top: 0%; left: 0%; width: 100%; height: 100%; background-color: grey; z-index:1001; -moz-opacity: 0.8; opacity:.80; filter: alpha(opacity=70);');
    body_el.appendChild(fade_div);
  }

  light_div.style.display='block';
  fade_div.style.display='block';
};

Selenium.prototype.doRemoveTitleCard = function() {
  this.browserbot.getDocument().getElementById('light').style.display='none';
  this.browserbot.getDocument().getElementById('fade').style.display='none';
};