//公共组件
import qz_calendar from "@comp/frames/calendar/calendar";
Vue.component("qz-calendar",qz_calendar);

import qz_alert from "@comp/frames/dialog/alert";
import qz_alert_theme from "@comp/frames/dialog/alert_theme";
import qz_dialog_index from "@comp/frames/dialog/index";
import qz_dropdown from "@comp/frames/dialog/others/dropdown";
import qz_modal from "@comp/frames/dialog/others/modal";
import qz_popover from "@comp/frames/dialog/others/popover";
import qz_tooltips from "@comp/frames/dialog/others/tooltips";
Vue.component("qz-alert",qz_alert);
Vue.component("qz-alert-theme",qz_alert_theme);
Vue.component("qz-dialog-index",qz_dialog_index);
Vue.component("qz-dropdown",qz_dropdown);
Vue.component("qz-modal",qz_modal);
Vue.component("qz-popover",qz_popover);
Vue.component("qz-tooltips",qz_tooltips);

import qz_form_index from "@comp/frames/form/index";
import qz_checkbox from "@comp/frames/form/base/checkbox";
import qz_img from "@comp/frames/form/base/img";
import qz_input from "@comp/frames/form/base/input";
import qz_radio from "@comp/frames/form/base/radio";
import qz_select from "@comp/frames/form/base/select";
import qz_textarea from "@comp/frames/form/base/textarea";
import qz_textarea_editor from "@comp/frames/form/base/textarea_editor";
import qz_upload from "@comp/frames/form/base/upload";
import qz_button from "@comp/frames/form/button/button";
import qz_button_group from "@comp/frames/form/button/button_group";
import qz_button_switch from "@comp/frames/form/button/button_switch";
import qz_form_dropdown from "@comp/frames/form/others/dropdown";
import qz_slider from "@comp/frames/form/others/slider";
import qz_slider_bar from "@comp/frames/form/others/slider_bar";
import qz_addresses from "@comp/frames/form/paragraph/addresses";
import qz_blockquote from "@comp/frames/form/paragraph/blockquote";
import qz_paragraph from "@comp/frames/form/paragraph/paragraph";
import qz_paragraph_list from "@comp/frames/form/paragraph/paragraph_list";
import qz_paragraph_list_table from "@comp/frames/form/paragraph/paragraph_list_table";
import qz_paragraph_media from "@comp/frames/form/paragraph/paragraph_media";
Vue.component("qz-form-index",qz_form_index);
Vue.component("qz-checkbox",qz_checkbox);
Vue.component("qz-img",qz_img);
Vue.component("qz-input",qz_input);
Vue.component("qz-radio",qz_radio);
Vue.component("qz-select",qz_select);
Vue.component("qz-textarea",qz_textarea);
Vue.component("qz-textarea-editor",qz_textarea_editor);
Vue.component("qz-upload",qz_upload);
Vue.component("qz-button",qz_button);
Vue.component("qz-button-group",qz_button_group);
Vue.component("qz-button-switch",qz_button_switch);
Vue.component("qz-form-dropdown",qz_form_dropdown);
Vue.component("qz-slider",qz_slider);
Vue.component("qz-slider-bar",qz_slider_bar);
Vue.component("qz-addresses",qz_addresses);
Vue.component("qz-blockquote",qz_blockquote);
Vue.component("qz-paragraph",qz_paragraph);
Vue.component("qz-paragraph-list",qz_paragraph_list);
Vue.component("qz-paragraph-list-table",qz_paragraph_list_table);
Vue.component("qz-paragraph-media",qz_paragraph_media);

import qz_layout_index from "@comp/frames/layout/index";
import qz_animate from "@comp/frames/layout/base/animate";
import qz_block from "@comp/frames/layout/base/block";
import qz_hr from "@comp/frames/layout/base/hr";
import qz_icon from "@comp/frames/layout/base/icon";
import qz_label from "@comp/frames/layout/base/label";
import qz_block_title from "@comp/frames/layout/header/block_title";
import qz_title from "@comp/frames/layout/header/title";
import qz_title_head from "@comp/frames/layout/header/title_head";
import qz_title_page from "@comp/frames/layout/header/title_page";
import qz_chat from "@comp/frames/layout/panel/chat";
import qz_collapse from "@comp/frames/layout/panel/collapse";
import qz_content_box from "@comp/frames/layout/panel/content_box";
import qz_jumbotron from "@comp/frames/layout/panel/jumbotron";
import qz_panel from "@comp/frames/layout/panel/panel";
import qz_panel_shortcut from "@comp/frames/layout/panel/panel_shortcut";
import qz_panel_title from "@comp/frames/layout/panel/panel_title";
import qz_tabs from "@comp/frames/layout/panel/tabs";
import qz_well from "@comp/frames/layout/panel/well";
Vue.component("qz-layout-index",qz_layout_index);
Vue.component("qz-animate",qz_animate);
Vue.component("qz-block",qz_block);
Vue.component("qz-hr",qz_hr);
Vue.component("qz-icon",qz_icon);
Vue.component("qz-label",qz_label);
Vue.component("qz-block-title",qz_block_title);
Vue.component("qz-title",qz_title);
Vue.component("qz-title-head",qz_title_head);
Vue.component("qz-title-page",qz_title_page);
Vue.component("qz-chat",qz_chat);
Vue.component("qz-collapse",qz_collapse);
Vue.component("qz-content-box",qz_content_box);
Vue.component("qz-jumbotron",qz_jumbotron);
Vue.component("qz-panel",qz_panel);
Vue.component("qz-panel-shortcut",qz_panel_shortcut);
Vue.component("qz-panel-title",qz_panel_title);
Vue.component("qz-tabs",qz_tabs);
Vue.component("qz-well",qz_well);

import qz_audio from "@comp/frames/media/audio";
import qz_carousel from "@comp/frames/media/carousel";
import qz_lightbox from "@comp/frames/media/lightbox";
import qz_video from "@comp/frames/media/video";
Vue.component("qz-audio",qz_audio);
Vue.component("qz-carousel",qz_carousel);
Vue.component("qz-lightbox",qz_lightbox);
Vue.component("qz-video",qz_video);

import qz_page_index from "@comp/frames/page/index";
Vue.component("qz-page-index",qz_page_index);

import qz_table_index from "@comp/frames/table/index";
import qz_table_base from "@comp/frames/table/table_base";
import qz_list from "@comp/frames/table/list/list";
Vue.component("qz-table-index",qz_table_index);
Vue.component("qz-table-base",qz_table_base);
Vue.component("qz-list",qz_list);

import qz_tree_index from "@comp/frames/tree/index";
Vue.component("qz-tree-index",qz_tree_index);

