<%--
  Created by IntelliJ IDEA.
  User: zuo
  Date: 2015/4/22
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../common/IncludeTop.jsp"%>
<script>
  function mouseMove1(event)
  {
    var move_div1 = document.getElementById("move_div1");
    var content ="<table><tr>"+"<td>"+"<a>AV-CB-01</a>"+"</td><td>Amazon Parrot</td>"+"</tr>";
    content+="<tr>"+"<td>"+"<a>AV-SB-02</a>"+"</td><td>Finch</td></tr></table>";
    move_div1.innerHTML = content;
    var x = event.x;
    var y = event.y;
    move_div1.style.left=x+10;
    move_div1.style.top=y;
    move_div1.style.visibility ='visible';
  }
  function mouseDown1()
  {
    document.getElementById("move_div1").style.visibility = 'hidden';
  }
  function mouseMove2(event)
  {
    var move_div1 = document.getElementById("move_div2");
    var content ="<table><tr>"+"<td>"+"<a>FI-FW-01</a>"+"</td><td>Koi</td>"+"</tr>";
    content+="<tr>"+"<td>"+"<a>FI-FW-02</a>"+"</td><td>Goldfish</td></tr>";
    content+="<tr>"+"<td>"+"<a>FI-SW-01</a>"+"</td><td>Angelfish</td></tr>";
    content+="<tr>"+"<td>"+"<a>FI-SW-02</a>"+"</td><td>Tiger Shark</td></tr></table>";
    move_div1.innerHTML = content;
    var x = event.x;
    var y = event.y;
    move_div1.style.left=x+10;
    move_div1.style.top=y;
    move_div1.style.visibility ='visible';
  }
  function mouseDown2()
  {
    document.getElementById("move_div2").style.visibility = 'hidden';
  }
  function mouseMove3(event)
  {
    var move_div1 = document.getElementById("move_div3");
    var content ="<table><tr>"+"<td>"+"<a>K9-BD-01</a>"+"</td><td>Bulldog</td>"+"</tr>";
    content+="<tr>"+"<td>"+"<a>K9-CW-01</a>"+"</td><td>Chihuahua</td></tr>";
    content+="<tr>"+"<td>"+"<a>K9-DL-01</a>"+"</td><td>Dalmation</td></tr>";
    content+="<tr>"+"<td>"+"<a>K9-PO-02</a>"+"</td><td>Poodle</td></tr>";
    content+="<tr>"+"<td>"+"<a>K9-RT-01</a>"+"</td><td>Golden Retriever</td></tr>";
    content+="<tr>"+"<td>"+"<a>K9-RT-02</a>"+"</td><td>Labrador Retriever</td></tr></table>";
    move_div1.innerHTML = content;
    var x = event.x;
    var y = event.y;
    move_div1.style.left=x+10;
    move_div1.style.top=y;
    move_div1.style.visibility ='visible';
  }
  function mouseDown3()
  {
    document.getElementById("move_div3").style.visibility = 'hidden';
  }
  function mouseMove4(event)
  {
    var move_div1 = document.getElementById("move_div4");
    var content ="<table><tr>"+"<td>"+"<a>RP-LI-02</a>"+"</td><td>Iguana</td>"+"</tr>";
    content+="<tr>"+"<td>"+"<a>RP-SN-01</a>"+"</td><td>Rattlesnake</td></tr></table>";
    move_div1.innerHTML = content;
    var x = event.x;
    var y = event.y;
    move_div1.style.left=x+10;
    move_div1.style.top=y;
    move_div1.style.visibility ='visible';
  }
  function mouseDown4()
  {
    document.getElementById("move_div4").style.visibility = 'hidden';
  }
  function mouseMove5(event)
  {
    var move_div1 = document.getElementById("move_div5");
    var content ="<table><tr>"+"<td>"+"<a>FL-DLH-02</a>"+"</td><td>Persian</td>"+"</tr>";
    content+="<tr>"+"<td>"+"<a>FL-DSH-01</a>"+"</td><td>Manx</td></tr></table>";
    move_div1.innerHTML = content;
    var x = event.x;
    var y = event.y;
    move_div1.style.left=x+10;
    move_div1.style.top=y;
    move_div1.style.visibility ='visible';
  }
  function mouseDown5()
  {
    document.getElementById("move_div5").style.visibility = 'hidden';
  }
</script>
<div id="Welcome">
  <div id="WelcomeContent">
    Welcome to myjpetstore!
  </div>
</div>

<div id="Main">
  <div id="Sidebar">
    <div id="SidebarContent">
      <a href="viewCategory?categoryId=FISH"><img src="../../../images/fish_icon.gif" /></a>
      <br/> Saltwater, Freshwater <br/>
      <a href="viewCategory?categoryId=DOGS"><img src="../../../images/dogs_icon.gif" /></a>
      <br /> Various Breeds <br />
      <a href="viewCategory?categoryId=CATS"><img src="../../../images/cats_icon.gif" /></a>
      <br /> Various Breeds, Exotic Varieties <br />
      <a href="viewCategory?categoryId=REPTILES"><img src="../../../images/reptiles_icon.gif" /></a>
      <br /> Lizards, Turtles, Snakes <br />
      <a href="viewCategory?categoryId=BIRDS"><img src="../../../images/birds_icon.gif" /></a>
      <br /> Exotic Varieties
    </div>
  </div>

  <div id="MainImage">
    <div id="MainImageContent">
      <map name="estoremap">
        <area alt="Birds" coords="72,2,280,250" href="viewCategory?categoryId=BIRDS" shape="rect" onmousemove="mouseMove1(event)"onmouseout="mouseDown1()"/>
        <div id="move_div1" style="position: absolute;background: #aaa"></div>
        <area alt="Fish" coords="2,180,72,250" href="viewCategory?categoryId=FISH"shape="rect" onmousemove="mouseMove2(event)"onmouseout="mouseDown2()"/>
        <div id="move_div2" style="position: absolute;background: #aaa"></div>
        <area alt="Dogs" coords="60,250,130,320" href="viewCategory?categoryId=DOGS"shape="rect" onmousemove="mouseMove3(event)"onmouseout="mouseDown3()"/>
        <div id="move_div3" style="position: absolute;background: #aaa"></div>
        <area alt="Reptiles" coords="140,270,210,340" href="viewCategory?categoryId=REPTILES" shape="rect" onmousemove="mouseMove4(event)"onmouseout="mouseDown4()"/>
        <div id="move_div4" style="position: absolute;background: #aaa"></div>
        <area alt="Cats" coords="225,240,295,310" href="viewCategory?categoryId=CATS" shape="rect" onmousemove="mouseMove5(event)"onmouseout="mouseDown5()"/>
        <div id="move_div5" style="position: absolute;background: #aaa"></div>
        <area alt="Birds" coords="280,180,350,250" href="viewCategory?categoryId=BIRDS" shape="rect" onmousemove="mouseMove1(event)"onmouseout="mouseDown1()"/>
      </map>
      <img height="355" src="../../../images/splash.gif" align="middle" usemap="#estoremap" width="350" />
    </div>
  </div>
  <div id="Separator">&nbsp</div>
</div>
<%@include file="../common/IncludeBottom.jsp"%>