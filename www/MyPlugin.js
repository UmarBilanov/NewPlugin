module.exports = {
    runBackground: function (successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "NewPlugin", "runBackground", [])
    }
}
