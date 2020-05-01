package com.team.compose.drawerdemo.home

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.layout.Center
import androidx.ui.layout.FlexColumn
import androidx.ui.material.TopAppBar
import androidx.ui.material.themeTextStyle
import com.team.compose.drawerdemo.R
import com.team.compose.drawerdemo.VectorImageButton

@Composable
fun HomeScreen(openDrawer: () -> Unit) {
    FlexColumn {
        inflexible {
            TopAppBar(
                title = { Text(text = "Compose Demo") },
                navigationIcon = {
                    VectorImageButton(R.drawable.ic_baseline_menu) {
                        openDrawer()
                    }
                }
            )
        }

        flexible(flex = 1f) {
            Center {
                Text(text = "Home Screen", style = +themeTextStyle { h4 })
            }
        }
    }
 }