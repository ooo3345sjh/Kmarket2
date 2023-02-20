/**
 *  cs
 */
$(function() {
  $('.more').click(function(e) {
    e.preventDefault();
    let list = $(this).parent();
    let items = list.find('> li:not(:last-child)');
    let hiddenItems = items.not(':visible');
    let isVisible = hiddenItems.length === 0;
    if (isVisible) {
      items.slice(3).slideUp(100, function() {
        $('.more a').text('더보기');
        $(this).css('display','none');
      });
    } else {
      hiddenItems.slideDown(100);
      $('.more a').text('간략히보기');
    }
  });
});