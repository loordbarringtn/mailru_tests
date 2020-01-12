Run from CI:<br>
parallel {<br>
gradle mailtests -Dbrowser=chrome -Dthreads=2<br>
gradle mailtests -Dbrowser=firefox -Dthreads=2<br>
}