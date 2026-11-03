package com.example.urcast

val sampleNodes = listOf(

    WeatherNode(
        name = "Dundalk Node",
        temperature = 14.0,
        humidity = 72,
        pressure = 1013,
        lightLevel = 450,
        lastRecorded = "10:20 AM",
        iconFileName = "haze_day",
        latitude = 54.0000,
        longitude = -6.4050,

        hardwareComponents = listOf(
            HardwareComponent(
                name = "Raspberry Pi",
                status = "Online",
                description = "Main controller operating normally"
            ),
            HardwareComponent(
                name = "GPS (NEO-6M)",
                status = "Connected",
                description = "GPS location available"
            ),
            HardwareComponent(
                name = "DHT22",
                status = "Working",
                description = "Temperature and humidity sensor"
            ),
            HardwareComponent(
                name = "BH1750",
                status = "Working",
                description = "Light sensor responding normally"
            )
        )
    ),

    WeatherNode(
        name = "Drogheda Node",
        temperature = 16.2,
        humidity = 65,
        pressure = 1015,
        lightLevel = 850,
        lastRecorded = "10:15 AM",
        iconFileName = "clear_day",
        latitude = 53.7179,
        longitude = -6.3561,

        hardwareComponents = listOf(
            HardwareComponent("Raspberry Pi", "Online", "Main controller operating normally"),
            HardwareComponent("GPS (NEO-6M)", "Connected", "GPS location available"),
            HardwareComponent("DHT22", "Working", "Temperature and humidity sensor"),
            HardwareComponent("BH1750", "Working", "Light sensor responding normally")
        )
    ),

    WeatherNode(
        name = "Dublin Node",
        temperature = 13.8,
        humidity = 80,
        pressure = 1009,
        lightLevel = 120,
        lastRecorded = "10:22 AM",
        iconFileName = "rainy_1_day",
        latitude = 53.3498,
        longitude = -6.2603,

        hardwareComponents = listOf(
            HardwareComponent("Raspberry Pi", "Online", "Main controller operating normally"),
            HardwareComponent("GPS (NEO-6M)", "Connected", "GPS location available"),
            HardwareComponent("DHT22", "Working", "Temperature and humidity sensor"),
            HardwareComponent("BH1750", "Working", "Light sensor responding normally")
        )
    )
)