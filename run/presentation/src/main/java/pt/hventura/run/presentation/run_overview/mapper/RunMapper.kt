package pt.hventura.run.presentation.run_overview.mapper

import pt.hventura.core.domain.run.Run
import pt.hventura.core.presentation.ui.formatted
import pt.hventura.core.presentation.ui.toFormattedHeartRate
import pt.hventura.core.presentation.ui.toFormattedKm
import pt.hventura.core.presentation.ui.toFormattedKmh
import pt.hventura.core.presentation.ui.toFormattedMeters
import pt.hventura.core.presentation.ui.toFormattedPace
import pt.hventura.run.presentation.run_overview.model.RunUi
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Run.toRunUi(): RunUi {
    val dateTimeInLocalTime = dateTimeUtc
        .withZoneSameInstant(ZoneId.systemDefault())
    val formattedDateTime = DateTimeFormatter
        .ofPattern("MMM dd, yyyy - hh:mma")
        .format(dateTimeInLocalTime)

    val distanceKm = distanceMeters / 1000.0

    return RunUi(
        id = id!!,
        duration = duration.formatted(),
        dateTime = formattedDateTime,
        distance = distanceKm.toFormattedKm(),
        avgSpeed = avgSpeedKmh.toFormattedKmh(),
        maxSpeed = maxSpeedKmh.toFormattedKmh(),
        pace = duration.toFormattedPace(distanceKm),
        totalElevation = totalElevationMeters.toFormattedMeters(),
        mapPictureUrl = mapPictureUrl,
        avgHeartRate = avgHeartRate.toFormattedHeartRate(),
        maxHeartRate = maxHeartRate.toFormattedHeartRate()
    )
}