/**
 * @providesModule MultiImageSelectorAndroid
 */

'use strict';

var React = require('react-native');
var {Platform, NativeModules} = React;
var RNMultiImageSelectorAndroid = NativeModules.MultiImageSelectorAndroid;

var MultiImageSelectorAndroid = {
    selectPictures(action, max_select_count) {
        return RNMultiImageSelectorAndroid.selectPictures(action, max_select_count);
    }
};

module.exports = MultiImageSelectorAndroid;
