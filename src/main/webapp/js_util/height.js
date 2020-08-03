$(document).ready(function () {
   var height=$(document).height();
   $('body').css('height',height);
   var mart=height-500;
   mart = mart/2;
   $('.loginBox').css('marginTop',mart);
})