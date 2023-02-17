/**
 *  cs
 */
$(function(){
        $('.more').click(function(e){
          e.preventDefault();


          let item = $(this).parent().find('> li:nth-child(n+4)');
          let isVisible = item.is(':visible');
          if(isVisible){
            item.slideUp(100);
          }else{
            item.slideDown(100);
          }
        });
      });