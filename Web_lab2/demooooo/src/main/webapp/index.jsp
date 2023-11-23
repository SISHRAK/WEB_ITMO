<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="aaaa.TableRow" %>
<%
    String title = "Лабораторная работа №2<br>по Веб-программированию<br>";
    String titleClass = "class=\"main_title column\"";
    if (request.getAttribute("title") != null) {
        title = request.getAttribute("title").toString();
        titleClass = "class=\"main_title column error_title\"";
    }
    List<TableRow> tableRows = (List<TableRow>) session.getAttribute("tableRows");
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Lab Tw0</title>
    <link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wrapper">
    <div class="content">
        <header>
            <div class="column logos_block">
                <div class="itmo_block">
                    Мегафакультет компьютерных технологий и управления <br>
                    Факультет программной инженерии и компьютерной техники
                </div>
            </div>
            <div <%=titleClass%>>
                <%= title%>
            </div>
            <div class="made_by column">
                Вариант: <a href="images/tz.png" target="_blank">3422</a><br>
                Выполнил: Барашко Арсений
                Александрович<br>
                Группа: P3234
            </div>
        </header>
        <div class="main_content">
            <h1>Попадёт ли точка на плоскости в заданную область?</h1>
            <div class="content_row">
                <div id="svg">
                    <svg xmlns="http://www.w3.org/2000/svg" height="300" width="300">
                        <line x1="0" y1="150" x2="300" y2="150" stroke="#000720"></line>
                        <line x1="150" y1="0" x2="150" y2="300" stroke="#000720"></line>
                        <line x1="270" y1="148" x2="270" y2="152" stroke="#000720"></line>
                        <text x="265" y="140">R</text>
                        <text x="290" y="140">x</text>
                        <text x="200" y="140">R/2</text>
                        <text x="75" y="140">-R/2</text>
                        <text x="20" y="140">-R</text>
                        <text x="156" y="35">R</text>
                        <text x="156" y="10">y</text>
                        <text x="156" y="95">R/2</text>
                        <text x="156" y="215">-R/2</text>
                        <text x="156" y="275">-R</text>
                        <polygon points="300,150 295,155 295, 145" fill="#000720" stroke="#000720"></polygon>
                        <polygon points="150,0 145,5 155,5" fill="#000720" stroke="#000720"></polygon>
                        <%--треугольник done--%>
                        <polygon points="30,150 150,150 150,30" fill-opacity="0.4" stroke="navy" fill="blue"></polygon>
                        <%--квадрат done--%>
                        <rect x="30" y="150" width="120" height="60" fill-opacity="0.4" stroke="navy"
                              fill="blue"></rect>
                        <%--четверть круга DONE--%>
                        <path d="M150 150 L 150 210 C 220 220 220 150 218 150 L Z" fill-opacity="0.4" stroke="navy"
                              fill="blue"></path>
                        <%
                            int width = 400; // Ширина графика в пикселях
                            int centre = width / 2;
                            if (tableRows != null) {
                                for (TableRow point : tableRows) {
                                    double xCoord = point.getX();
                                    double yCoord = point.getY();
                                    double rPrev = point.getR();
                                    double transformedX = xCoord * 115 / rPrev + centre;
                                    double transformedY = yCoord * 115 * -1 / rPrev + centre;
                        %>
                        <circle cx="<%= transformedX - 50  %>" cy="<%= transformedY - 50 %>" r="5"
                                fill="red"></circle>
                        <%
                                }
                            }
                        %>

                    </svg>
                </div>
                <div class="column">
                    <form method="get" action="result.jsp" id="sendForm" class="send_form">
                        <div class="x_block coordinate_block">
                            <label for="x_select">X: </label>
                            <input type="radio" id="x_select" name="coordinate_x" value="-5"/><a>-5</a>
                            <input type="radio" id="x_select1" name="coordinate_x" value="-4"/><a>-4</a>
                            <input type="radio" id="x_select2" name="coordinate_x" value="-3"/><a>-3</a>
                            <input type="radio" id="x_select3" name="coordinate_x" value="-2"/><a>-2</a>
                            <input type="radio" id="x_select4" name="coordinate_x" value="-1"/><a>-1</a>
                            <input type="radio" id="x_select5" name="coordinate_x" value="0"/><a>0</a>
                            <input type="radio" id="x_select6" name="coordinate_x" value="1"/><a>1</a>
                            <input type="radio" id="x_select7" name="coordinate_x" value="2"/><a>2</a>
                            <input type="radio" id="x_select8" name="coordinate_x" value="3"/><a>3</a>
                        </div>
                        <div class="y_block coordinate_block">
                            <label for="y_select">Y: </label>
                            <input type="text" id="y_select" name="coordinate_y" autocomplete="off"
                                   placeholder="Число в диапазоне (-5; 3)" maxlength="6">
                        </div>
                        <label for="r_select">R: </label>
                        <input type="text" id="r_select" name="coordinate_r" autocomplete="off"
                               placeholder="(2; 5)" maxlength="6">
                        <div class="send_button_block">
                            <br>    </br>
                            <button type="submit" class="send_button">Отправить</button>
                        </div>
                    </form>
                </div>
                <div class="column result_table">
                    <table>
                        <tr>
                            <th>X</th>
                            <th>Y</th>
                            <th>R</th>
                            <th>Время запуска</th>
                            <th>Результат</th>
                        </tr>
                        <%
                            if (tableRows != null)
                                for (TableRow tableRow1ableRow : tableRows) {
                        %>
                        <tr>
                            <td class="x_cell"><%= tableRow1ableRow.getX() %>
                            </td>
                            <td class="y_cell"><%= tableRow1ableRow.getY() %>
                            </td>
                            <td class="r_cell"><%= tableRow1ableRow.getR() %>
                            </td>
                            <td><%= tableRow1ableRow.getLocalTime() %>
                            </td>
                            <td><%= tableRow1ableRow.getInside() %>
                            </td>
                        </tr>
                        <%}%>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <footer class="footer">
        <div class="footer_text">
            Санкт-Петербург<br>
            Ноябрь, 2023 год
        </div>
    </footer>
</div>
<script src="js/form.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</body>
</html>