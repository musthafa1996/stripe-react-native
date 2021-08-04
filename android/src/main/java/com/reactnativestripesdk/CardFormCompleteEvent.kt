package com.reactnativestripesdk

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.facebook.react.uimanager.events.Event
import com.facebook.react.uimanager.events.RCTEventEmitter

internal class CardFormCompleteEvent constructor(viewTag: Int, private val cardDetails: MutableMap<String, Any>?, private val complete: Boolean, private val dangerouslyGetFullCardDetails: Boolean) : Event<CardChangedEvent>(viewTag) {
  override fun getEventName(): String {
    return EVENT_NAME
  }

  override fun dispatch(rctEventEmitter: RCTEventEmitter) {
    rctEventEmitter.receiveEvent(viewTag, eventName, serializeEventData())
  }

  private fun serializeEventData(): WritableMap {
    val eventData = Arguments.createMap()

    if (cardDetails == null) {
      return eventData
    }
    eventData.putString("brand", cardDetails["brand"]?.toString())
    eventData.putString("last4", cardDetails["last4"]?.toString())
    eventData.putString("country", cardDetails["country"]?.toString())
    eventData.putInt("expiryMonth", cardDetails["expiryMonth"] as Int)
    eventData.putInt("expiryYear", cardDetails["expiryYear"] as Int)
    eventData.putBoolean("complete", complete)
    eventData.putString("postalCode", cardDetails["postalCode"]?.toString())

    if (dangerouslyGetFullCardDetails) {
      eventData.putString("number", cardDetails["number"]?.toString()?.replace(" ", ""))
    }

    return eventData
  }

  companion object {
    const val EVENT_NAME = "onFormComplete"
  }

}
