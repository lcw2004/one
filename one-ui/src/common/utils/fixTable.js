let $ = require('jquery')

function fixTable (tableId, fixColumnNumber, width, height) {
  // 查找_tableLayout，如果没有，则添加
  let tableLayout = $('#' + tableId + '_tableLayout')
  if (tableLayout.length !== 0) {
    tableLayout.before($('#' + tableId))
    tableLayout.empty()
  } else {
    $('#' + tableId).after('<div id="' + tableId + '_tableLayout" style="overflow:hidden;height:' + height + 'px; width:' + width + 'px;"></div>')
  }
  $('<div id="' + tableId + '_tableFix"></div>' + '<div id="' + tableId + '_tableHead"></div>' + '<div id="' + tableId + '_tableColumn"></div>' + '<div id="' + tableId + '_tableData"></div>').appendTo('#' + tableId + '_tableLayout')

  // --------------
  // 旧table
  let oldtable = $('#' + tableId)

  let tableFixClone = oldtable.clone(true)
  tableFixClone.attr('id', tableId + '_tableFixClone')
  let tableFix = $('#' + tableId + '_tableFix')
  tableFix.append(tableFixClone)

  let tableHeadClone = oldtable.clone(true)
  tableHeadClone.attr('id', tableId + '_tableHeadClone')
  let tableHead = $('#' + tableId + '_tableHead')
  tableHead.append(tableHeadClone)

  let tableColumnClone = oldtable.clone(true)
  tableColumnClone.attr('id', tableId + '_tableColumnClone')
  let tableColumn = $('#' + tableId + '_tableColumn')
  tableColumn.append(tableColumnClone)
  let tableData = $('#' + tableId + '_tableData')
  tableData.append(oldtable)
  $('#' + tableId + '_tableLayout table').each(function () {
    $(this).css('margin', '0')
  })
  // --------------

  // 计算表格固定部分的宽度和高度
  let headHeight = $('#' + tableId + '_tableHead thead').height() + 2
  tableHead.css('height', headHeight)
  tableFix.css('height', headHeight)
  let columnsWidth = 0
  $('#' + tableId + '_tableColumn tr:last td:lt(' + fixColumnNumber + ')').each(function () {
    columnsWidth += $(this).outerWidth(true)
  })

  tableColumn.css('width', columnsWidth)
  tableFix.css('width', columnsWidth)
  tableData.scroll(function () {
    tableHead.scrollLeft(tableData.scrollLeft())
    tableColumn.scrollTop(tableData.scrollTop())
  })

  tableFix.css({
    'overflow': 'hidden',
    'position': 'relative',
    'z-index': '50',
    'background-color': '#f9f9f9'
  })
  tableHead.css({
    'overflow': 'hidden',
    'width': width - 17,
    'position': 'relative',
    'z-index': '45',
    'background-color': '#ffffff'
  })
  tableColumn.css({
    'overflow': 'hidden',
    'height': height - 17,
    'position': 'relative',
    'z-index': '40',
    'background-color': '#f9f9f9'
  })
  tableData.css({
    'overflow-x': 'scroll',
    'overflow-y': 'hidden',
    'width': width,
    'height': height,
    'position': 'relative',
    'z-index': '35'
  })

  if (tableHead.width() > $('#' + tableId + '_tableFix table').width()) {
    tableHead.css('width', $('#' + tableId + '_tableFix table').width())
    tableData.css('width', $('#' + tableId + '_tableFix table').width() + 17)
  }
  if (tableColumn.height() > $('#' + tableId + '_tableColumn table').height()) {
    tableColumn.css('height', $('#' + tableId + '_tableColumn table').height())
    tableData.css('height', $('#' + tableId + '_tableColumn table').height() + 17)
  }

  tableFix.offset($('#' + tableId + '_tableLayout').offset())
  tableHead.offset($('#' + tableId + '_tableLayout').offset())
  tableColumn.offset($('#' + tableId + '_tableLayout').offset())
  tableData.offset($('#' + tableId + '_tableLayout').offset())
}

export default fixTable
