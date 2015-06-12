<%--
  Created by IntelliJ IDEA.
  User: zuo
  Date: 2015/4/22
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
    window.onload = init;
    var xhr = false;
    var keyword;
    var statesArray = new Array();
    function init() {
        if (window.XMLHttpRequest) {
            xhr = new XMLHttpRequest();
        }
        else {
            if (window.ActiveXObject) {
                try {
                    xhr = new ActiveXObject("Microsoft.XMLHTTP");
                }
                catch (e) { }
            }
        }
        getKeyword();
    }
    function getKeyword(){
        document.getElementById("searchField").onkeyup = searchSuggest;
    }
    function searchSuggest() {
        keyword = document.getElementById("searchField").value;
        document.getElementById("searchField").className = "";
        getData();
    }
    function getData(){
        if(keyword!="") {
            if (xhr) {
                xhr.onreadystatechange = setStatesArray;
                xhr.open("GET", "searchSuggest?keyword=" + keyword, true);
                xhr.send(null);
            }
            else {
                alert("Sorry, but I couldn't create an XMLHttpRequest");
            }
        }else{
            statesArray = "";
            showSuggest();
        }

    }
    function setStatesArray() {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                var a = xhr.responseText;
                statesArray = a.split("|");
                showSuggest();
            }
            else {
                alert("There was a problem with the request " + xhr.status);
            }
        }
    }

    function showSuggest(){
        var div_suggest =document.getElementById("searchSuggest");
        div_suggest.className ="divcss5";
        //div_suggest.innerHTML = "";
        for (var i=0; i<statesArray.length;i++) {
            var thisState = statesArray[i];
            var tempDiv = document.createElement("div");
            tempDiv.innerHTML = thisState;
            tempDiv.onclick = makeChoice;
            tempDiv.className = "divcss6";
            div_suggest.appendChild(tempDiv);
        }
        if(div_suggest.style.visibility=='hidden')
        {
            div_suggest.style.visibility ='visible';
        }
    }
    function makeChoice(evt) {
        if (evt) {
            var thisDiv = evt.target;
        }
        else {
            var thisDiv = window.event.srcElement;
        }
        document.getElementById("searchField").value = thisDiv.innerHTML;
        var div_suggest =document.getElementById("searchSuggest");
        div_suggest.innerHTML = "";
        div_suggest.className ="";
        if(div_suggest.style.visibility=='visible')
        {
            div_suggest.style.visibility ='hidden';
        }

    }
</script>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
  <link rel="StyleSheet" href="../../../css/jpetstore.css" type="text/css"
        media="screen" />

  <meta name="generator"
        content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
  <title>myjpetstore</title>
  <meta content="text/html; charset=windows-1252"
        http-equiv="Content-Type" />
  <meta http-equiv="Cache-Control" content="max-age=0" />
  <meta http-equiv="Cache-Control" content="no-cache" />
  <meta http-equiv="expires" content="0" />
  <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
  <meta http-equiv="Pragma" content="no-cache" />
</head>

<body>

<div id="Header">

  <div id="Logo">
    <div id="LogoContent">
      <a href="main"><img src="../../../images/logo-topbar.gif" /></a>
    </div>
  </div>

  <div id="Menu">
    <div id="MenuContent">
      <a href="viewCart"><img align="middle" name="img_cart" src="../images/cart.gif" /></a> <img align="middle" src="../images/separator.gif" />
        <s:if test="#session.account == null">
            <a href="signonForm">Sign In</a>
            <img align="middle" src="../images/separator.gif" />
        </s:if>
        <s:if test="#session.account != null">
                <a href="signOff">Sign Out</a>
                <img align="middle" src="../images/separator.gif" />
                <a href="myCount">My Account</a>
                <img align="middle" src="../images/separator.gif" />
        </s:if>
        <a href="viewLog">Log </a><img align="middle" src="../images/separator.gif" /><a href="help">?</a>
    </div>
  </div>

  <div id="Search">
    <div id="SearchContent">
        <form action="searchProduct" method="POST">
            <s:textfield theme="simple" name="keyword" size="14" id="searchField" autocomplete="off"/>
            <input type="submit" name="searchProducts" value="Search" />
        </form>
    </div>
      <div id="searchSuggest">
      </div>

</div>

  <div id="QuickLinks">
    <a href="viewCategory?categoryId=FISH"><img
            src="../../../images/sm_fish.gif" /></a> <img src="../../../images/separator.gif" />
    <a href="viewCategory?categoryId=DOGS"><img
            src="../../../images/sm_dogs.gif" /></a> <img src="../../../images/separator.gif" />
    <a href="viewCategory?categoryId=REPTILES"><img
            src="../../../images/sm_reptiles.gif" /></a> <img
          src="../../../images/separator.gif" />
      <a href="viewCategory?categoryId=CATS"><img
          src="../../../images/sm_cats.gif" /></a> <img src=".../../../images/separator.gif" />
    <a href="viewCategory?categoryId=BIRDS"><img
            src="../../../images/sm_birds.gif" /></a>
  </div>

</div>

<div id="Content">
