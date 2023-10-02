<?php
session_start();


if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['x'], $_POST['y'], $_POST['r'])) {
        $x = floatval($_POST['x']);
        $y = floatval($_POST['y']);
        $r = floatval($_POST['r']);

        if (is_numeric($x) && is_numeric($y) && is_numeric($r) && $r > 0 && $r <= 5 && abs($x) <= 2 && abs($y) <= 5) {
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
    if ($x >= 0 && $x <= $r && $y >= 0 && $y <= $r / 2) {
        return true;
    }
    if ($x >= 0 && $y >= 0 && $x + $y <= $r) {
        return true;
    }

    return false;
}

?>

<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Лаба</title>

    <style>
        body {
            font-family: serif;
            color: #333;
            font-size: 16px;
            margin: 0;
            padding: 20px;
        }

        h1 {
            margin-bottom: 20px;
        }

        table {
            width: 50%;
            border-collapse: collapse;
            margin-bottom: 1px;
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

        .error {
            color: red;
        }
    </style>
</head>

<body>
<h1>Барашко Арсений Александрович</h1>
<h1>Группа Р3234 вариант 1519</h1>
<p><img src="2.jpg"></p>

<?php if (isset($_SESSION['points']) && !empty($_SESSION['points'])): ?>
    <h2>Результаты</h2>
    <table>
        <thead>
        <tr>
            <th>Координата X</th>
            <th>Координата Y</th>
            <th>Радиус R</th>
            <th>Результат</th>
            <th>Дата и время</th>
            <th>Время выполнения</th>
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
<form method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>">
    <label for="x">Координата X:</label>
    <input type="number" id="x" name="x" required>
    <label for="y">Координата Y:</label>
    <input type="number" id="y" name="y" required>
    <label for="r">Радиус R:</label>
    <input type="number" id="r" name="r" required>
    <input type="submit" value="Проверить">
</form>

<script>
    document.querySelector('form').addEventListener('submit', function (e) {
        var x = parseFloat(document.getElementById('x').value);
        var y = parseFloat(document.getElementById('y').value);
        var r = parseFloat(document.getElementById('r').value);

        if (isNaN(x) || isNaN(y) || isNaN(r)) {
            e.preventDefault();
            document.getElementById('validation-error').classList.remove('hidden');
        }
    });
</script>
</body>

</html>
