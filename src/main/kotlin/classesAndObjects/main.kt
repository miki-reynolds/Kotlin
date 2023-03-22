package classesAndObjects

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


//primary constructor
//class SmartDevice(val name: String, val category: String) {
//    var deviceStatus = "online"
//
//    secondary constructor (we can have many 2nd constructors)
//    constructor(name: String, category: String, statusCode: Int) : this(name, category) {
//        deviceStatus = when (statusCode) {
//            0 -> "offline"
//            1 -> "online"
//            else -> "unknown"
//        }
//    }
//    ...
//}


// open indicates it can be inherited
open class SmartDevice(val name: String, val category: String) {

    // declare extra properties besides the properties already indicated in the parameters (primary constructor)
    var deviceStatus = "online"
        protected set

    open val deviceType = "unknown"

    open fun turnOn() {
        deviceStatus = "on"
    }

    open fun turnOff() {
        deviceStatus = "off"
    }
}


// Smart TV IS-A smart device.
class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart TV"

    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)
    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    override fun turnOn() {
        super.turnOn()
        println(
            "$name is turned on. Speaker volume set to $speakerVolume and channel number is " +
                    "set to $channelNumber."
        )
    }

    override fun turnOff() {
        super.turnOff()
        println("$name turned off")
    }

    fun increaseSpeakerVolume() {
        speakerVolume++
        println("Speaker volume increased to $speakerVolume.")
    }

    fun nextChannel() {
        channelNumber++
        println("Channel number increased to $channelNumber.")
    }
}


// Smart Light IS-A smart device.
class SmartLightDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart Light"
    private var brightnessLevel by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("$name is turned on. The brightness level is $brightnessLevel.")
    }

    override fun turnOff() {
        super.turnOff()
        brightnessLevel = 0
        println("$name turned off")
    }

    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel.")
    }
}

// The SmartHome class HAS-A smart TV device and smart light
class SmartHome(val smartTvDevice: SmartTvDevice, val smartLightDevice: SmartLightDevice) {

    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }

    fun turnOffTv() {
        deviceTurnOnCount--
        smartTvDevice.turnOff()
    }

    fun increaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }

    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }

    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }

    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}


// instead of having to use backing field for getter and mainly setter, we can create this class
class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {

    private var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}


fun main() {
    val smartHome = SmartHome(
        SmartTvDevice(deviceName = "Android TV", deviceCategory = "Entertainment"),
        SmartLightDevice(deviceName = "Google light", deviceCategory = "Utility")
    )

    smartHome.turnOnTv()
    smartHome.turnOnLight()
    println("Total number of devices currently turned on: ${smartHome.deviceTurnOnCount}")
    println()

    smartHome.increaseTvVolume()
    smartHome.changeTvChannelToNext()
    smartHome.increaseLightBrightness()
    println()

    smartHome.turnOffAllDevices()
    println("Total number of devices currently turned on: ${smartHome.deviceTurnOnCount}.")
}


/*
Modifier

           Accessible in same class    Accessible in subclass    Accessible in same module    Accessible outside module

private             o                               x                       x                               x
protected           o                               o                       x                               x
internal            o                               o                       o                               x
public              o                               o                       o                               o
 */