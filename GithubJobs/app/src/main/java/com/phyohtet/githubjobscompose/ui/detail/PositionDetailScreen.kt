package com.phyohtet.githubjobscompose.ui.detail

import android.os.Build
import android.text.Html
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Clip
import androidx.ui.core.Text
import androidx.ui.core.WithDensity
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.ripple.Ripple
import androidx.ui.res.imageResource
import androidx.ui.res.vectorResource
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview
import com.phyohtet.githubjobscompose.R
import com.phyohtet.githubjobscompose.model.dto.JobPositionDTO
import com.phyohtet.githubjobscompose.ui.Screen
import com.phyohtet.githubjobscompose.ui.appThemeColors
import com.phyohtet.githubjobscompose.ui.navigateTo

@Composable
fun PositionDetailScreen(dto: JobPositionDTO) {
    val image = +imageResource(R.drawable.placeholder)
    val vector = +vectorResource(R.drawable.ic_baseline_arrow_back)

    VerticalScroller {
        Column(
            crossAxisSize = LayoutSize.Expand
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "Job Detail",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    Ripple(bounded = false) {
                        Clickable(onClick = {
                            navigateTo(Screen.Positions)
                        }) {
                            WithDensity {
                                Container(
                                    width = vector.defaultWidth.toDp(),
                                    height = vector.defaultHeight.toDp()
                                ) {
                                    DrawVector(vector, Color.Transparent)
                                }
                            }
                        }
                    }
                }
            )

            Column(
                modifier = Spacing(16.dp)
            ) {

                Container(expanded = true, height = 180.dp) {
                    Clip(shape = RoundedCornerShape(8.dp)) {
                        DrawImage(image)
                    }
                }

                HeightSpacer(height = 16.dp)

                Text(
                    text = dto.title ?: "Title",
                    style = +themeTextStyle { h6 },
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    "${dto.type ?: "Remote"} / ${dto.location ?: "New York"}",
                    style = (+themeTextStyle { body2 }).withOpacity(0.6f)
                )

                HeightSpacer(height = 8.dp)

                Divider(color = Color(0x14333333))

                HeightSpacer(height = 8.dp)

                val desc = dto.description?.let {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Html.fromHtml(it.trim { it <= ' ' }, Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        Html.fromHtml(it.trim { it <= ' ' })
                    }
                } ?: "Job Description\n\n\n"

                Text(desc.toString(), style = (+themeTextStyle { body2 }))

            }
        }
    }
}

@Preview
@Composable
fun PositionDetailScreenPreview() {
    MaterialTheme(
        colors = appThemeColors
    ) {
        PositionDetailScreen(JobPositionDTO())
    }
}