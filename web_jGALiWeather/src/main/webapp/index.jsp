<!DOCTYPE html>
<html>
    <head>
        <meta charset=utf-8>
        <title>Councils - jGALiWeather</title>
        <meta name="Keywords" content="councils, short term, mid term, meteorology, meteorologic, forecast, jGALiWeather, Meteogalicia" />
        <meta name="Description" content="Short term and mid term forecast for galician councils elaborated by Meteogalicia and jGALiWeather" />
        <meta name="robots" content="index, follow">
        <link rel="icon"  type="image/x-icon" href="images/favicon.ico"/>
        <meta property="og:locale" content="es_ES"/>
        <meta property="og:type" content="website"/>
        <meta property="og:title" content="Concellos - MeteoGalicia"/>
        <meta property="og:description" content="Short term and mid term forecast for galician councils elaborated by Meteogalicia and jGALiWeather"/>
        <meta property="og:site_name" content="jGALiWeather"/>

        <link rel="stylesheet" type="text/css" href="styles/index.css">
        <link rel="stylesheet" type="text/css" href="styles/temperatures.css">
        <script type="text/javascript" src="lib/jquery-2.2.3.min.js" ></script>
        <script type="text/javascript" src="lib/moment.js" ></script>
        <script type="text/javascript" src="lib/moment-timezone-with-data-2010-2020.js" ></script>
        <script type="text/javascript" src="lib/searchScripts.js" ></script>
        <script type="text/javascript" src="lib/services.js" ></script>    
    </head>

    <body>
        <div id="content">
            <header class="headerPrincipal">              
            </header>
            
            <div id="forecast">
                <h1>Council forecast</h1>
            </div>

            <aside>
                <h2>Councils</h2>
                <nav>
                    <ul>
                        <li><a class="menuCitiesElement" onclick="getForecastData(15030)">A Coru�a</a></li>
                        <li><a class="menuCitiesElement" onclick="getForecastData(15036)">Ferrol</a></li>
                        <li><a class="menuCitiesElement" onclick="getForecastData(27028)">Lugo</a></li>
                        <li><a class="menuCitiesElement" onclick="getForecastData(32054)">Ourense</a></li>
                        <li><a class="menuCitiesElement" onclick="getForecastData(36038)">Pontevedra</a></li>
                        <li><a class="menuCitiesElement" onclick="getForecastData(15078)">Santiago de Compostela</a></li>
                        <li><a class="menuCitiesElement" onclick="getForecastData(36057)">Vigo</a></li>
                    </ul>
                </nav>

                <h2 class="anotherCouncil">Search another council</h2>

                <form id="setCouncil" name="setCouncil" action="setCouncil" method="get">

                    <h3>Choose a province:</h3>
                    <div class="radioCorunaOurense">
                        <input type="radio" name="province" id="coruna" value="Coruna" checked><label for="coruna">A Coru�a</label><br>
                        <input type="radio" name="province" id="ourense" value="Ourense"><label for="ourense">Ourense</label>
                    </div>
                    <div class="radioPontevedraLugo">
                        <input type="radio" name="province" id="lugo" value="Lugo"><label for="lugo">Lugo</label><br>
                        <input type="radio" name="province" id="pontevedra" value="Pontevedra"><label for="pontevedra">Pontevedra</label>
                    </div>

                    <div id="chooser">
                        <h3>Choose a council in <span id="councilChooserTitle"></span>:</h3>
                        <select id="selectCouncil" onchange="selectChange()" name="council" size="1">
                        </select>
                    </div>
                </form>

                <div class="box">
                    <h4>Useful Links</h4>
                    <a href="http://www.meteogalicia.es/web/predicion/localidades/lendaLoc.action?request_locale=gl">Legend</a>
                </div>
            </aside>

            <main>
                <article>
                    <header>
                        <h2>Short term forecast for <span id="tableTitle"></span></h2>   
                    </header>

                    <table>
                        <thead>
                            <tr>
                                <th class="title">Tabla</th>
                                <th class="dayHeader" id="dayHeader1" colspan=3></th>
                                <th class="dayHeader" id="dayHeader2" colspan=3></th>
                                <th class="dayHeader" id="dayHeader3" colspan=3></th>
                                <th class="dayHeader" id="dayHeader4" colspan=3></th>
                            </tr>

                            <tr>
                                <th class="title">Period</th>

                                <th class="periodHeader">Morning</th>
                                <th class="periodHeader">Afternoon</th>
                                <th class="periodHeader">Night</th>

                                <th class="periodHeader">Morning</th>
                                <th class="periodHeader">Afternoon</th>
                                <th class="periodHeader">Night</th>

                                <th class="periodHeader">Morning</th>
                                <th class="periodHeader">Afternoon</th>
                                <th class="periodHeader">Night</th>

                                <th class="periodHeader">Morning</th>
                                <th class="periodHeader">Afternoon</th>
                                <th class="periodHeader">Night</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="title">Sky State</td>

                                <td><img id="skMorning1"></td>
                                <td><img id="skAfternoon1"></td>
                                <td><img id="skNight1"></td>

                                <td><img id="skMorning2"></td>
                                <td><img id="skAfternoon2"></td>
                                <td><img id="skNight2"></td>

                                <td><img id="skMorning3"></td>
                                <td><img id="skAfternoon3"></td>
                                <td><img id="skNight3"></td>

                                <td><img id="skMorning4"></td>
                                <td><img id="skAfternoon4"></td>
                                <td><img id="skNight4"></td>
                            </tr>

                            <tr>
                                <td class="title">Wind</td>

                                <td><img id="windMorning1"></td>
                                <td><img id="windAfternoon1"></td>
                                <td><img id="windNight1"></td>

                                <td><img id="windMorning2"></td>
                                <td><img id="windAfternoon2"></td>
                                <td><img id="windNight2"></td>

                                <td><img id="windMorning3"></td>
                                <td><img id="windAfternoon3"></td>
                                <td><img id="windNight3"></td>

                                <td><img id="windMorning4"></td>
                                <td><img id="windAfternoon4"></td>
                                <td><img id="windNight4"></td>
                            </tr>

                            <tr>
                                <td class="title">Rain probability</td>

                                <td>5%</td>
                                <td>5%</td>
                                <td>5%</td>

                                <td>5%</td>
                                <td>5%</td>
                                <td>5%</td>

                                <td>5%</td>
                                <td>5%</td>
                                <td>5%</td>

                                <td>5%</td>
                                <td>5%</td>
                                <td>5%</td>
                            </tr>

                            <tr>
                                <td class="title" rowspan=2>Temperatura</td>

                                <td class="tempLabels" colspan=3>
                                    <div class="min_label">
                                        MIN
                                    </div>
                                    <div class="max_label">
                                        MAX
                                    </div>
                                </td>

                                <td class="tempLabels" colspan=3>
                                    <div class="min_label">
                                        MIN
                                    </div>
                                    <div class="max_label">
                                        MAX
                                    </div>
                                </td>

                                <td class="tempLabels" colspan=3>
                                    <div class="min_label">
                                        MIN
                                    </div>
                                    <div class="max_label">
                                        MAX
                                    </div>
                                </td>

                                <td class="tempLabels" colspan=3>
                                    <div class="min_label">
                                        MIN
                                    </div>
                                    <div class="max_label">
                                        MAX
                                    </div>
                                </td>
                            </tr>       

                            <tr>
                                <td colspan=3>
                                    <div class="min_temp" id="tempMin1"></div>
                                    <div class="max_temp" id="tempMax1"></div>
                                </td>

                                <td colspan=3>
                                    <div class="min_temp" id="tempMin2"></div>
                                    <div class="max_temp" id="tempMax2"></div>
                                </td>

                                <td colspan=3>
                                    <div class="min_temp" id="tempMin3"></div>
                                    <div class="max_temp" id="tempMax3"></div>
                                </td>

                                <td colspan=3>
                                    <div class="min_temp" id="tempMin4"></div>
                                    <div class="max_temp" id="tempMax4"></div>
                                </td>
                            </tr>

                            <tr>
                                <td class="title">Air Quality</td>

                                <td colspan=3>Green</td>

                                <td colspan=3>Green</td>

                                <td colspan=3>Yellow</td>

                                <td colspan=3>-</td>
                            </tr>

                            <tr>
                                <td class="title">Comment</td>

                                <td id="comment" colspan=12></td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="divNote">
                        <img class="noteImg" width="23" src="images/users2.png" alt="Short Term forecast for councils">
                        <span class="dataSource_title">Data source:</span>
                        <span class="dataSource_text">
                            Forecast shown in this section are made by MeteoGalicia's Operative Forecasting team.		
                        </span>
                    </div>
                    
                </article>
            </main>

            <footer>
                <span class="design">Developed by Diego Iglesias Freire</span><span class="contact">Contact on: <a class="mail" href="mailto:diego.iglesias.freire@rai.usc.es?Subject=jGALiWeather">diego.iglesias.freire@rai.usc.es</a></span>
            </footer>
        </div>
    </body>
</html>
