package com.phyohtet.githubjobscompose.ui.positions

import android.text.format.DateUtils
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.ui.core.*
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.ripple.Ripple
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview
import com.phyohtet.githubjobscompose.R
import com.phyohtet.githubjobscompose.model.ApiResource
import com.phyohtet.githubjobscompose.model.NetworkState
import com.phyohtet.githubjobscompose.model.dto.JobPositionDTO
import com.phyohtet.githubjobscompose.ui.Screen
import com.phyohtet.githubjobscompose.ui.appThemeColors
import com.phyohtet.githubjobscompose.ui.navigateTo
import com.phyohtet.githubjobscompose.ui.observe
import java.util.*

@Composable
fun PositionsScreen(liveData: LiveData<ApiResource<List<JobPositionDTO>>>) {

    val data = +observe(liveData)

    FlexColumn  {
        inflexible {
            TopAppBar(title = { Text("GithubJobs") })
        }
        when (data?.state) {
            NetworkState.LOADING -> {
                expanded(1f) {
                    Align(alignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }
            NetworkState.LOADED -> {
                expanded(1f) {
                    VerticalScroller {
                        Column {
                            data.data?.forEach {
                                PositionItem(dto = it) {
                                    navigateTo(Screen.PositionDetail(it))
                                }
                                Divider(color = Color(0x14333333))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PositionItem(dto: JobPositionDTO, onClick: () -> Unit) {
    val image = +imageResource(R.drawable.placeholder)
    Ripple(bounded = true, color = +themeColor { primary }) {
        Clickable(onClick = onClick) {
            Padding(16.dp) {
                FlexRow {
                    inflexible {
                        Container(width = 65.dp, height = 65.dp) {
                            Clip(RoundedCornerShape(4.dp)) {
                                DrawImage(image)
                            }
                        }
                    }

                    expanded(1f) {
                        Container(
                            height = 65.dp,
                            expanded = true,
                            padding = EdgeInsets(left = 16.dp)
                        ) {
                            FlexColumn(
                                crossAxisSize = LayoutSize.Expand
                            ) {
                                inflexible {
                                    Text(
                                        text = dto.title ?: "Title",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            color = Color.Black,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1
                                    )
                                }

                                expanded(1f) {
                                    val time = dto.createdAt?.let {
                                        DateUtils.getRelativeTimeSpanString(
                                            it.time,
                                            Calendar.getInstance().timeInMillis,
                                            DateUtils.MINUTE_IN_MILLIS
                                        )
                                    }
                                    Text(
                                        text = time?.toString() ?: "time ago",
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            color = Color.Black
                                        ).withOpacity(0.6f)
                                    )
                                }

                                inflexible {
                                    FlexRow {
                                        expanded(1f) {
                                            Text(
                                                text = dto.company ?: "Microsoft",
                                                style = (+themeTextStyle { body2 }).withOpacity(0.6f),
                                                overflow = TextOverflow.Ellipsis,
                                                maxLines = 1
                                            )
                                        }

                                        inflexible {
                                            WidthSpacer(16.dp)
                                            Text(
                                                text = dto.type ?: "Full Time",
                                                style = TextStyle(
                                                    color = Color(0xFF669900),
                                                    fontWeight = FontWeight.Bold
                                                )
                                            )
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PositionItemPreview() {
    MaterialTheme(
        colors = appThemeColors
    ) {
        PositionItem(JobPositionDTO()) {

        }
    }
}

@Preview
@Composable
fun PositionsScreenPreview() {
    val data = MutableLiveData<ApiResource<List<JobPositionDTO>>>()
    data.value = ApiResource(arrayListOf(
        JobPositionDTO(),
        JobPositionDTO(),
        JobPositionDTO(),
        JobPositionDTO(),
        JobPositionDTO(),
        JobPositionDTO(),
        JobPositionDTO(),
        JobPositionDTO()
    ), NetworkState.LOADED)
    MaterialTheme(
        colors = appThemeColors
    ) {
        PositionsScreen(data)
    }
}