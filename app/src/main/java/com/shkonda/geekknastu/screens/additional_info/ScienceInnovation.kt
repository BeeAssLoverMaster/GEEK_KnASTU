package com.shkonda.geekknastu.screens.additional_info

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.ContactMail
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.PhoneEnabled
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shkonda.geekknastu.R
import com.shkonda.geekknastu.ui.components.AssetImage
import com.shkonda.geekknastu.ui.theme.BlueTopBar
import com.shkonda.geekknastu.ui.theme.DarkGreen
import com.shkonda.geekknastu.ui.theme.MainGreen
import com.shkonda.geekknastu.ui.theme.White
import com.shkonda.geekknastu.util.ListItem

@Composable
fun ScienceInnovation(itemList: ListItem) {
    val bullet = "\u2022"
    val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .height(250.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(4.dp, BlueTopBar)
            ) {
                AssetImage(
                    imageName = itemList.imageName,
                    contentDescription = itemList.title,
                    modifier = Modifier.fillMaxSize()
                )
            }

            /*СРОКИ ПРОВЕДЕНИЯ*/
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "calendar",
                        modifier = Modifier
                            .weight(0.8f)
                            .size(50.dp),
                        tint = MainGreen
                    )
                    Column(
                        Modifier
                            .fillMaxSize()
                            .weight(2f)
                            .padding(end = 8.dp, bottom = 8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Сроки проведения",
                            fontSize = 25.sp,
                            color = BlueTopBar,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "${itemList.dateStart} - ${itemList.dateEnd}",
                            fontSize = 15.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Box(modifier = Modifier
                            .width(160.dp)
                            .background(
                                color = DarkGreen,
                                shape = RoundedCornerShape(4.dp)
                            ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Идёт регистрация",
                                color = White
                            )
                        }
                    }
                }
            }

            /*ПЛОЩАДКА*/
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = "place",
                        modifier = Modifier
                            .weight(0.8f)
                            .size(50.dp),
                        tint = MainGreen
                    )
                    Column(
                        Modifier
                            .fillMaxSize()
                            .weight(2f)
                            .padding(end = 8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Площадка",
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp,
                            color = BlueTopBar,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "г. Комсомольск-на-Амуре\nФГБОУ ВО \"КнАГУ\", пр. Ленина, 27",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                }
            }


            /*КОНТАКТНАЯ ИНФОРМАЦИЯ*/
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ContactMail,
                        contentDescription = "contact_mail",
                        modifier = Modifier
                            .weight(0.8f)
                            .size(50.dp),
                        tint = MainGreen
                    )
                    Column(
                        Modifier
                            .fillMaxSize()
                            .weight(2f)
                            .padding(end = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Контактная\nинформация",
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp,
                            color = BlueTopBar
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 40.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Mail,
                                contentDescription = "mail",
                                Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text(
                                text = "zhurbina.in@email.knastu.ru",
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 40.dp, bottom = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.PhoneEnabled,
                                contentDescription = "phone_enabled",
                                Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.padding(5.dp))
                            Text(
                                text = "+7 962-286-99-80",
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }
                    }
                }

            }

            /*ЦЕЛЬ / ЗАДАЧА*/
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(start = 8.dp, top = 8.dp, end = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Checklist,
                            contentDescription = "checklist",
                            modifier = Modifier
                                .weight(0.5f)
                                .size(50.dp),
                            tint = MainGreen
                        )
                        Text(
                            text = "Цель / задача",
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp,
                            color = BlueTopBar,
                            modifier = Modifier.weight(2f)
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.science_1),
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                        lineHeight = 20.sp
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Секции:",
                        fontSize = 25.sp,
                        color = BlueTopBar
                    )
                    val bullet = "\u2022"
                    val messages1 = listOf(
                        "Секция 1. Машиностроение, материаловедение и химические технологии",
                        "Секция 2. Транспортно-энергетический комплекс, авиационная и морская техника",
                        "Секция 3. Энергетика, электроника и управление",
                        "Секция 4. Инновации в строительстве",
                        "Секция 5. Информационно-коммуникационные технологии"
                    )
                    Text(
                        buildAnnotatedString {
                            messages1.forEach {
                                withStyle(style = paragraphStyle) {
                                    append(bullet)
                                    append("\t\t")
                                    append(it)
                                }
                            }
                        },
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                        lineHeight = 20.sp,
                        color = BlueTopBar
                    )
                }
            }
        }
    }
}