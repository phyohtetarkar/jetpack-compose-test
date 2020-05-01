package com.team.compose.drawerdemo.list

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.FlexColumn
import androidx.ui.layout.Padding
import androidx.ui.material.Divider
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.themeTextStyle
import com.team.compose.drawerdemo.R
import com.team.compose.drawerdemo.VectorImageButton

@Composable
fun ListScreen(openDrawer: () -> Unit) {
    
    val items = arrayOfNulls<String>(25)
    
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
        expanded(flex = 1f) {
            VerticalScroller {
                Column {
                    items.withIndex().forEach { (index, _) ->
                        ListItem(title = "Item ${index + 1}")
                        Divider(color = Color(0x14333333))
                    }
                }
            }
        }
    }
}

@Composable
private fun ListItem(title: String) {
    Ripple(bounded = false) {
        Clickable {
            Container(expanded = true, alignment = Alignment.CenterLeft) {
                Padding(padding = 16.dp) {
                    Text(title, style = +themeTextStyle { body2 })
                }
            }
        }
    }
}