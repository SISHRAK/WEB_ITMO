<?php
session_start();


if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    if (isset($_POST['x'], $_POST['y'], $_POST['r'])) {
        
        $x = $_POST['x'];
        $y = $_POST['y'];
        $r = $_POST['r'];
        
        if (is_numeric($x) && is_numeric($y) && is_numeric($r) && $r > 0 && $r <= 5 && abs($x) <= 2 && $y < 5 && $y > -5) {
            $result = is_point_in_area($x, $y, $r);
            $_SESSION['points'][] = [
                'x' => $x,
                'y' => $y,
                'r' => $r,
                'result' => $result ? 'Да' : 'Нет',
                'timestamp' => date('Y-m-d H:i:s'),
                'execution_time' => microtime(true) - $_SERVER['REQUEST_TIME_FLOAT']
            ];
            header('Location: lab.php');
            exit();
        } else{
            print "Введите корректные значения";
        }
    }
}

function is_point_in_area($x, $y, $r)
{
    if ($x >= -$r / 2 && $x <= 0 && $y >= 0 && $y <= $r) {
        return true;
    }
    if($x * $x + $y * $y <= $r * $r && $x <= 0 && $y <= 0){
        return true;
    }
    if($x <= $r && $y >= -1 * $r && $y <= 0){
        return true;
    }
    return false;
}

?>

<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
<style>
    body {
            font-family: serif;
            color: #333;
            font-size: 16px;
            margin: 0;
            padding: 20px;
        }

        h1 {
            font-family: serif;
            margin-bottom: 18px;
            color: #333;
            font-size: 14px;
            margin: 0;
            padding: 18px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ccc;
        }

        form {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="number"] {
            width: 20%;
            padding: 5px;
            border: 1px solid #ccc;
        }

        input[type="submit"] {
            background-color: #333;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
        }

        table[type="userInputTable"] {
            background-color: blueviolet;
            width: 50%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        table[type="userInputTable"] th {
            background-color: blueviolet;
        }

        .highlight > h1{
            background-color: yellow;
        }

        .error {
            color: red;
        }

        .info {
            font-style: italic;
        }
    </style>
</head>

<body>
<h1>Барашко Арсений Александрович</h1>
<h1>Группа Р3234 вариант 1519</h1>
<p><img src="2.jpg"></p>

<?php if (isset($_SESSION['points']) && !empty($_SESSION['points'])): ?>
    <h2 class = "highlight">Результаты</h2>
    <table>
        <thead>
        <tr>
            <th>Координата X</th>
            <th>Координата Y</th>
            <th>Радиус R</th>
            <th>Результат</th>
            <th>Дата и время</th>
            <td> Время выполнения</td>
        </tr>
        </thead>
        <tbody>
        <?php foreach ($_SESSION['points'] as $point): ?>
            <tr>
                <td><?php echo $point['x']; ?></td>
                <td><?php echo $point['y']; ?></td>
                <td><?php echo $point['r']; ?></td>
                <td><?php echo $point['result']; ?></td>
                <td><?php echo $point['timestamp']; ?></td>
                <td><?php echo round($point['execution_time'], 2); ?> сек.</td>
            </tr>
        <?php endforeach; ?>
        </tbody>
    </table>
<?php endif; ?>

<h2>Проверка точки</h2>
<script> jQuery(document).ready(function($) {
  $('#submit').prop({disabled: true});
  $('input[type="checkbox"]').change(function() {
    $('#submit').prop({disabled: false});
  });
});</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<form method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>">
            <table id="userInputTable">
                <tr class="form">
                    <th class="form" id="formFirstColumn">
                        <p>Выберите значение <strong>X</strong>:</p>

                        <input type="checkbox" id="x" name="x" value=-2>
                        <label for="x">-2</label>
                        <br>

                        <input type="checkbox" id="x" name="x" value=-1.5>
                        <label for="x">-1.5</label>
                        <br>

                        <input type="checkbox" id="x" name="x" value=-1>
                        <label for="x">-1</label>
                        <br>

                        <input type="checkbox" id="x" name="x" value=-0.5>
                        <label for="x">-0.5</label>
                        <br>

                        <input type="checkbox" id="x" name="x" value=0>
                        <label for="x">0</label>
                        <br>

                        <input type="checkbox" id="x" name="x" value=0.5>
                        <label for="x">0.5</label>
                        <br>

                        <input type="checkbox" id="x" name="x" value=1>
                        <label for="x">1</label>
                        <br>

                        <input type="checkbox" id="x" name="x" value=1.5>
                        <label for="x">1.5</label>
                        <br>

                        <input type="checkbox" id="x" name="x" value=2>
                        <label for="x">2</label>
                    </th>
                    <script>
        const checkboxes = document.querySelectorAll('input[name="x"]');
        checkboxes.forEach((checkbox) => {
            checkbox.addEventListener('change', () => {
                checkboxes.forEach((cb) => {
                    if (cb !== checkbox) {
                        cb.checked = false;
                    }
                });
            });
        });
    </script>

                    <td class="form">
                        <p>Введите значение <strong>Y</strong>:</p>
                        <input type="text" id="y" name="y" class="text-input" />
                        <br>
                    </td>

                    <th class="form">
                        <p>Выберите значение <strong>R</strong>:</p>

                        <input type="checkbox" id="r" name="r" value=1>
                        <label for="1">1</label>
                        <br>

                        <input type="checkbox" id="r" name="r" value=2>
                        <label for="r">2</label>
                        <br>

                        <input type="checkbox" id="r" name="r" value=3>
                        <label for="r">3</label>
                        <br>

                        <input type="checkbox" id="r" name="r" value=4>
                        <label for="r">4</label>
                        <br>

                        <input type="checkbox" id="r" name="r" value=5>
                        <label for="r">5</label>
                    </th>
                    <script>
        const checkboxe = document.querySelectorAll('input[name="r"]');
        checkboxe.forEach((checkbox) => {
            checkbox.addEventListener('change', () => {
                checkboxe.forEach((cb) => {
                    if (cb !== checkbox) {
                        cb.checked = false;
                    }
                });
            });
        });
    </script>
                    <td><input type="submit" value="Проверить"></td>
                </tr>
            </table>
</form>
<script>
    document.querySelector('queryForm').addEventListener('submit', function (e) {
        var x = parseFloat(document.getElementById('x').value);
        var y = parseFloat(document.getElementById('y').value);
        var r = parseFloat(document.getElementById('r').value);

        if (isNaN(x) || isNaN(y) || isNaN(r) || r < 0 && r > 5 && abs(x) > 2 && y > 5 || y < -5 ) {
            e.preventDefault();
            document.getElementById('validation-error').classList.remove('hidden');
        }
    });
</script>
</body>

</html>
