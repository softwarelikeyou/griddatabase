<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>

html { 
	height: 100%; 
	width: 100%;
	font-family: Verdana,Tahoma,Arial,Helvetica,sans-serif; 
}

body { 
	padding: 0; 
	margin: 0; 
}

a { 
	color: #004488;
}

a.sly-action img { 
	padding-right: 2px;	
}

/*
a.sly-action:hover { 
	color: #0088BB; 	
	border: 1px solid #7EAAC6;
	padding: 3px;
}

a.sly-action-button:hover { 
	color: #0088BB; 	
	border: 1px solid #7EAAC6;
	padding: 3px;
}
*/

.z-window-embedded-tr,.z-window-embedded-tl {
	display: none;	
}

.z-window-embedded-tr,.z-window-embedded-tl,.z-window-embedded-hl,.z-window-embedded-hr,.z-window-embedded-hm {
	padding: 0; 
	background-image: none;
}

.z-window-embedded-header {
	background: transparent url(${c:encodeURL('~./gray/img/sidebar-highlighted.png')}) repeat-x scroll 0pt 0pt;
	padding-right: 5px;
	padding-top: 5px;		
}

.sly-window-caption {
	color: #353535; 
	font-size: 115%;
	vertical-align: middle;
	font-weight: bold;
	padding-left: 5px;
}

.z-listheader .z-listheader-cnt { 
	
}

.z-listheader-btn {
	background: transparent no-repeat left center;
	display: none; position: absolute; width: 14px; right: 0; top: 0; z-index: 15;
	cursor: pointer;
	background-image: url(${c:encodeURL('~./gray/zul/img/grid/hd-btn.png')});
}

.z-listheader-over .z-listheader-btn, .z-listheader-visi .z-listheader-btn {
	display: block;
}

a.z-listheader-btn:hover {
	background-position: -14px center;
}

.z-listheader-over {
	background: #ACDDF9 repeat-x 0 0;
	background-image: url(${c:encodeURL('~./gray/zul/img/grid/column-over.png')});
}

.z-listhead-menu-grouping .z-menu-item-img {
	background-image:url(${c:encodeURL('~./gray/zul/img/grid/menu-group.png')});
}

.z-listhead-menu-asc .z-menu-item-img {
	background-image:url(${c:encodeURL('~./gray/zul/img/grid/menu-arrowup.png')});
}

.z-listhead-menu-dsc .z-menu-item-img {
	background-image:url(${c:encodeURL('~./gray/zul/img/grid/menu-arrowdown.png')});
}

tr.z-listitem-seld {
	background: #E1E1FF none repeat scroll 0 0;
}

tr.z-listitem-over-seld {
	background: #ECECFF none repeat scroll 0 0;
}

tr.z-listitem-over {
	background: #F2F2FF none repeat scroll 0 0;
}


div.z-listbox-footer * td { 
	vertical-align: middle;
}

/*
.z-combobox-readonly { 
	background: #FFFFFF url(${c:encodeURL('~./gray/zul/img/misc/text-bg.gif')}) repeat-x scroll 0 0;	
}

.z-combobox-readonly-disd { 
	background: #E9E9E9 none repeat scroll 0 0 !important;	
}
*/

td.z-row-inner { 
	vertical-align: middle;
}

.z-checkbox, .z-checkbox label, .z-checkbox label img, .z-radio, .z-radio label { 
	vertical-align: middle;
	white-space: nowrap;
}

.z-checkbox input, .z-radio input { 
	margin: 0 2px 0 2px;
	vertical-align: middle;
}

.z-checkbox img, .z-radio img { 
	margin: 0 2px 0 2px;
	vertical-align: middle;
	margin-bottom: .25em;
}

.z-hbox * .z-label, .z-hbox * td { 
	vertical-align: middle;
}

.z-imagelabel { 
	font-family: Verdana,Tahoma,Arial,Helvetica,sans-serif;
	font-size: 12px;
	font-weight: normal;
}

.z-imagelabel img { 
	padding: 0px;
	margin-bottom: .2em;
	vertical-align: middle;
}

.z-initing { 
	background: transparent no-repeat scroll center center !important;
}

.sly-listheader .z-listheader-cnt, .sly-column .z-column-cnt { 
	color: #353535;
	font-weight: bold;	
}

.sly-smaller-listheader .z-listheader-cnt, .sly-smaller-column .z-column-cnt { 
	color: #353535;
	font-weight: bold;
	font-size: x-small;	
}

.sly-listheader * img {
	vertical-align: middle; 
}

.sly-list-cell { 
	border-right: 1px solid #cccccc; 
	border-bottom: 1px solid #cccccc; 
	border-collapse: collapse;
	white-space: nowrap;
	overflow: hidden;
}

.sly-groupbox { 
	margin: 5px 5px 10px 5px;
}

.sly-groupbox .z-groupbox-cnt { 
	padding: 0;	
}

.sly-groupbox .z-groupbox-cnt { 
	padding: 0;	
}

.sly-groupbox .z-groupbox-cnt .z-grid { 
	border: 0;	
}

.sly-groupbox .z-groupbox-cnt .z-label { 
	margin: 3px;	
}

.sly-groupbox .z-groupbox-cnt .z-vbox { 
	margin-top: 3px;
}

.sly-groupbox .z-groupbox-cnt .z-vbox .z-hbox { 
	margin-left: 3px;
}

.sly-groupbox .z-groupbox-cnt .z-vbox .z-grid { 
	border-top: 1px solid #ABABAB;
}

.sly-caption { 
	vertical-align: middle;
	color: #004488;
	font-weight: bold;
	font-size: 110%;
}

.sly-tab * .z-tab-text { 
	vertical-align: middle;
	color: #004488;
	font-weight: bold;
}

.sly-wizard-center .z-panel-children { 
	background: #FCFCFC;
}

.sly-wizard-listitem { 
	color: #353535;
	vertical-align: middle;
	white-space: nowrap;
	text-decoration: none;	
}

.sly-wizard-listsubitem { 
	padding-left: 1em;
}

.sly-wizard-listitem:hover { 
	color: #0090F6;
}

.sly-wizard-listitem-active { 
	font-weight: bold;
	color: #0090F6;
	text-shadow: 0.1em 0.1em 0.125em #AAAAAA;
}

.sly-wizard-listitem-active:hover { 
	font-weight: bold;
	color: #00457B;
}

span.sly-wizard-panel-header {
	color: #0090F6; 
	font-size: 115%;
	vertical-align: middle;
	font-weight: bold;
	padding-left: 5px;
}

/*
tr.sly-wizard-grid-row > td.z-detail-outer { 
	vertical-align: middle;
}
*/
	
div.sly-invisible-grid, 
div.sly-invisible-grid > .z-grid-body, 
div.sly-invisible-grid > .z-grid-body > table > tbody.z-rows > tr.z-row > td.z-row-inner, 
div.sly-invisible-grid > .z-grid-body > table > tbody.z-rows > tr.z-row > .z-cell, 
div.sly-invisible-grid > .z-grid-body > table > tbody.z-rows > tr.z-grid-odd {
	border: 0;
	background: transparent;
	vertical-align: middle;
}

.sly-info-label, .sly-info-label .z-checkbox-cnt, .sly-info-label .z-radio-cnt { 
	font-weight: bold;
	color: #353535;
	vertical-align: middle;
	white-space: nowrap;
}

.sly-info-label img, .sly-info-label .z-checkbox-cnt input, .sly-info-label .z-radio-cnt input { 
	vertical-align: middle;
	white-space: nowrap;
}


.sly-checkbox { 
	text-align: left; 
	white-space: nowrap; 
	vertical-align: middle;
}

.sly-sidebar-item { 
	vertical-align: middle; 
	white-space: nowrap; 
	text-decoration: none; 
	width: 100%;
	height: 30px;
	display: block; 
	padding: 0; 
	margin: 0; 
	background: transparent url(${c:encodeURL('~./gray/zul/img/layout/borderlayout-hm.png')}) repeat-x scroll 0pt 0pt;
	font-weight: bold;
	color: #004488;
	border: 0;
}

.sly-sidebar-item-highlighted { 
	vertical-align: middle; 
	white-space: nowrap; 
	text-decoration: none; 
	width: 100%;
	height: 30px;
	display: block; 
	padding: 0; 
	margin: 0; 
	background: transparent url(${c:encodeURL('~./gray/img/sidebar-highlighted.png')}) repeat-x scroll 0pt 0pt;
	font-weight: bold;
	color: #004488;
	border: 0;
}
sly-sidebar-item
.sly-sidebar-item * .z-toolbarbutton-cnt, .sly-sidebar-item-highlighted * .z-toolbarbutton-cnt { 
	font-weight: bold;
	padding: 3px;
}

.sly-browser-link img { 
	padding-right: 5px;
}

.sly-list-header { 
	vertical-align: middle; 
	white-space: nowrap; 
	text-decoration: none; 
	width: 100%; 
	display: block; 
	padding: 4px; 
	margin: 0; 
	background: transparent url(${c:encodeURL('~./gray/zul/img/layout/borderlayout-hm.png')}) repeat-x scroll 0pt 0pt;
}

.sly-button-footer { 
	background: transparent url(${c:encodeURL('~./gray/zul/img/layout/borderlayout-hm.png')}) repeat-x scroll 0pt 0pt;
}

.sly-button-toolbar > .z-toolbar-body { 
	width: 100%;
}

.sly-select-all-checkbox { 
	vertical-align: middle;
	text-align: center;
}

.sly-header-bar { 
	background: transparent url(${c:encodeURL('~./gray/img/top-bar-gradient.png')}) repeat-x scroll 0pt -1px;
	padding: 6px;
	color: #eeeeee;
	font-family:tahoma,arial,verdana,sans-serif;
	font-size:11px;
	font-weight:bold;
}

.sly-top-bar { 
	background: transparent url(${c:encodeURL('~./gray/img/top-bar-gradient.png')}) repeat-x scroll 0pt -1px;
	color: #eeeeee;
	font-family:tahoma,arial,verdana,sans-serif;
	font-size:11px;
	font-weight:bold;
	margin: 0;
	padding: 0;	
	height: 100%;
}

.sly-top-bar * td { 
	vertical-align: middle;
}

.sly-top-bar * a img { 
	padding-right: 3px;	
}

.sly-device-button { 
	margin: 5px;
	font-size: smaller;
}

.sly-running-status { 
	margin: 3px;
}

.sly-detail-stats-box tbody tr table tbody tr td, .sly-detail-stats-box tbody tr table tbody tr td div { 
	vertical-align: top;
}

.sly-databases-grid * .z-grid-pgi-b, .sly-databases-grid * .z-paging, .sly-databases-grid * .z-grid-footer, .sly-databases-grid > .z-grid-footer, .sly-databases-grid * .z-grid { 
	background: transparent !important;
	border: 0;
}

.sly-database-row, .sly-database-children-row, .sly-database-tables-row, .sly-database-views-row, .sly-database-stored-procedures-row { 
	background: none !important;
}

.sly-tree-red > .z-row-inner, .sly-tree-green > .z-row-inner, .sly-tree-blue > .z-row-inner { 
	border: 0 !important;
}

.sly-database-row > .z-detail-outer, .sly-tree-red > .z-detail-outer, .sly-red-checkbox-cell { 
	background: #FFE5E5 url(${c:encodeURL('~./gray/zul/img/detail-bg-red.png')}) repeat-y scroll left center !important;
	border: 0 !important;
}

.sly-database-children-row > .z-detail-outer, .sly-database-checkbox-cell, .sly-tree-blue > .z-detail-outer, .sly-blue-checkbox-cell { 
	background: #E5E5FF url(${c:encodeURL('~./gray/zul/img/detail-bg-blue.png')}) repeat-y scroll left center !important;
	border: 0 !important;
}

.sly-database-tables-row > .z-detail-outer, .sly-database-stored-procedures-row > .z-detail-outer, .sly-database-views-row > .z-detail-outer, .sly-tree-green > .z-detail-outer, .sly-green-checkbox-cell { 
	background: #E5FFE5 url(${c:encodeURL('~./gray/zul/img/detail-bg-green.png')}) repeat-y scroll left center !important;
	border: 0 !important;
}

.sly-database-table-row > .z-detail-outer, .sly-database-stored-procedure-row > .z-detail-outer, .sly-database-view-row > .z-detail-outer, .sly-database-table-child-row > .z-detail-outer {
	border: 0 !important;
}

.sly-tree-datagrid { 
	background: transparent !important;
}

.sly-tree-datagrid-columns { 
	background-color: transparent !important;
	background-image: none !important;
} 

th.sly-tree-column-header { 
	border: 0 !important;
	background: transparent !important;
	font-weight: bold !important;
	color: #353535 !important;
	vertical-align: middle;
	white-space: nowrap;
	text-decoration: underline !important;	
}

/*
.sly-database-instances-grid > .z-grid-body { 
	overflow: auto;
	height: 100%;
}
*/

.sly-over-hard-quota { 
	color: red;
	font-weight: bold;
}

.sly-over-soft-quota { 
	color: orange;
	font-weight: bold;
}

.sly-actions-panel { 
	padding: 3px;
}

div.sly-progressmeter-small {
	background:#f0f0f0 repeat-x 0 0;
	background-image:url(${c:encodeURL('~./gray/zul/img/misc/prgmeter_bg.png')});
	border:1px solid #cdcdcd;
	text-align:left;
	height:13px;
	overflow:hidden;
}

span.sly-progressmeter-small {
	display:-moz-inline-box;
	display:inline-block;
	background:#e8e8e8 repeat-x left center;
	background-image:url(${c:encodeURL('~./gray/zul/img/misc/prgmeter.png')});
	height:13px;
	line-height:0;
	font-size:0;
}

div.sly-progressmeter-small-red {
	background:#f0f0f0 repeat-x 0 0;
	background-image:url(${c:encodeURL('~./gray/zul/img/misc/prgmeter_bg.png')});
	border:1px solid #cdcdcd;
	text-align:left;
	height:13px;
	overflow:hidden;
}

span.sly-progressmeter-small-red-img {
	display:-moz-inline-box;
	display:inline-block;
	background:#DA0000 repeat-x left center;
	background-image:url(/images/prgmeter-small-red.png);
	height:13px;
	line-height:0;
	font-size:0;
}

div.sly-progressmeter-small-green {
	background:#f0f0f0 repeat-x 0 0;
	background-image:url(${c:encodeURL('~./gray/zul/img/misc/prgmeter_bg.png')});
	border:1px solid #cdcdcd;
	text-align:left;
	height:13px;
	overflow:hidden;
}

span.sly-progressmeter-small-green-img {
	display:-moz-inline-box;
	display:inline-block;
	background:#1B8B3D repeat-x left center;
	background-image:url(/images/prgmeter-small-green.png);
	height:13px;
	line-height:0;
	font-size:0;
}

div.sly-progressmeter-small-yellow {
	background:#f0f0f0 repeat-x 0 0;
	background-image:url(${c:encodeURL('~./gray/zul/img/misc/prgmeter_bg.png')});
	border:1px solid #cdcdcd;
	text-align:left;
	height:13px;
	overflow:hidden;
}

span.sly-progressmeter-small-yellow-img {
	display:-moz-inline-box;
	display:inline-block;
	background:#FFFF00 repeat-x left center;
	background-image:url(/images/prgmeter-small-yellow.png);
	height:13px;
	line-height:0;
	font-size:0;
}

div.sly-progressmeter-small-purple {
	background:#f0f0f0 repeat-x 0 0;
	background-image:url(${c:encodeURL('~./gray/zul/img/misc/prgmeter_bg.png')});
	border:1px solid #cdcdcd;
	text-align:left;
	height:13px;
	overflow:hidden;
}

span.sly-progressmeter-small-purple-img {
	display:-moz-inline-box;
	display:inline-block;
	background:#9999cc repeat-x left center;
	background-image:url(/images/prgmeter-small-purple.png);
	height:13px;
	line-height:0;
	font-size:0;
}

.sly-bold { 
	font-weight: bold;
}

.sly-bold-red { 
	color: red;
	font-weight: bold;
}

.sly-bold-green { 
	color: green;
	font-weight: bold;
}

.sly-bold-yellow { 
	color: #AAAA00;
	font-weight: bold;
}

.sly-nowrap { 
	white-space: nowrap;
}

.z-label img { 
	padding-right: 5px;
	vertical-align: middle;
}

table.z-vbox tbody tr td table { 
	width: 100%;
}

.sly-red { 
	color: red;
}

.sly-light-gray { 
	color: #666666;
}

.sly-smaller-text, .sly-smaller-text > label, .sly-smaller-text > span { 
	font-size: x-small;
}

.sly-red-background { 
	background-color: #FFDDDD !important;
}

.sly-red-border { 
	border: 2px solid #F00;
}

.sly-yellow-background { 
	background-color: #FFFFDD !important;
}

.sly-yellow-border { 
	border: 2px solid #FF0;
}

.sly-green-background { 
	background-color: #DDFFDD !important;
}

.sly-green-border { 
	border: 2px solid #0F0;
}

tr.sly-yellow-background.z-listitem-seld.z-listitem-over-seld,
tr.sly-red-background.z-listitem-seld.z-listitem-over-seld {
	background-color: #ECECFF !important;	
}

tr.sly-yellow-background.z-listitem-over,
tr.sly-red-background.z-listitem-over {
	background-color: #F2F2FF !important;
}

tr.sly-yellow-background.z-listitem-seld, tr.sly-red-background.z-listitem-seld { 
	background-color: #E1E1FF !important;	
}

table.z-hbox.sly-dashboard-meter-row * td { 
	text-align: center;
	vertical-align: middle; 
} 