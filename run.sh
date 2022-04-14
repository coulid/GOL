echo "[*] Compiling"
echo "[*] javac src/*.java -d classes"
javac src/*.java -d classes/

echo "[*] RUNNING"
echo "[*] java com.tui.GOL"


java -cp classes com.tui.Gol

