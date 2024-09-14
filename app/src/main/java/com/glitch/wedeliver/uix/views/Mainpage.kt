package com.glitch.wedeliver.uix.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mainpage() {
	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					Text(text = "Kişiler")
				},
				actions = {}
			)
		},
		floatingActionButton = {
			FloatingActionButton(
				onClick = { },
				content = {
					/*Icon(
						painter = painterResource(id = R.drawable.ekle_resim),
						contentDescription = ""
					)*/
				}
			)
		},
	) { paddingValues ->
		LazyColumn(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues)
		) {
			items(
				count = 0,
				itemContent = {//0,1,2

				}
			)
		}
	}
}