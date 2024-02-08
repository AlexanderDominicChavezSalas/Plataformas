--
-- Archivo generado con SQLiteStudio v3.4.4 el jue. Feb. 8 02:26:45 2024
--
-- Codificación de texto usada: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Tabla: Medicina
CREATE TABLE IF NOT EXISTS Medicina (MedCod INTEGER PRIMARY KEY AUTOINCREMENT, MedNom TEXT NOT NULL, MedDes TEXT, UniMedCod TEXT CONSTRAINT UniMedCod REFERENCES UnidadMedicina (UniMedCod) NOT NULL, MedDos NUMERIC NOT NULL, MedNiv INTEGER NOT NULL, MedCon INTEGER NOT NULL DEFAULT (1), MedEstReg TEXT NOT NULL);

-- Tabla: Recordatorio
CREATE TABLE IF NOT EXISTS Recordatorio (RecCod INTEGER PRIMARY KEY AUTOINCREMENT, MedCod INTEGER CONSTRAINT MedCod REFERENCES Medicina (MedCod) NOT NULL, RecFre INTEGER NOT NULL, RecFecIni INTEGER NOT NULL, RecFecFin INTEGER NOT NULL, RecEstReg TEXT NOT NULL);

-- Tabla: UnidadMedicina
CREATE TABLE IF NOT EXISTS UnidadMedicina (UniMedCod INTEGER PRIMARY KEY AUTOINCREMENT, UniMedNom TEXT NOT NULL, UniMedAli TEXT, UniMedEstReg TEXT NOT NULL DEFAULT A);
INSERT INTO UnidadMedicina (UniMedCod, UniMedNom, UniMedAli, UniMedEstReg) VALUES (1, 'Miligramo', 'mg', 'A');
INSERT INTO UnidadMedicina (UniMedCod, UniMedNom, UniMedAli, UniMedEstReg) VALUES (2, 'Mililitro', 'mL', 'A');
INSERT INTO UnidadMedicina (UniMedCod, UniMedNom, UniMedAli, UniMedEstReg) VALUES (3, 'Centímetro cúbico', 'cc', 'A');
INSERT INTO UnidadMedicina (UniMedCod, UniMedNom, UniMedAli, UniMedEstReg) VALUES (4, 'Cucharada', 'cda', 'A');
INSERT INTO UnidadMedicina (UniMedCod, UniMedNom, UniMedAli, UniMedEstReg) VALUES (5, 'Onza', 'oz ', 'A');
INSERT INTO UnidadMedicina (UniMedCod, UniMedNom, UniMedAli, UniMedEstReg) VALUES (6, 'Litro', 'L', 'A');
INSERT INTO UnidadMedicina (UniMedCod, UniMedNom, UniMedAli, UniMedEstReg) VALUES (7, 'Gramo', 'g', 'A');
INSERT INTO UnidadMedicina (UniMedCod, UniMedNom, UniMedAli, UniMedEstReg) VALUES (8, 'Gota', 'gota', 'A');
INSERT INTO UnidadMedicina (UniMedCod, UniMedNom, UniMedAli, UniMedEstReg) VALUES (9, 'Pastilla', 'pastilla', 'A');

-- Tabla: Usuario
CREATE TABLE IF NOT EXISTS Usuario (UsuCod INTEGER PRIMARY KEY AUTOINCREMENT, UsuNom TEXT NOT NULL, UsuApePat TEXT NOT NULL, UsuApeMat TEXT NOT NULL, UsuCorEle TEXT NOT NULL, UsuPas TEXT NOT NULL, UsuEstReg TEXT NOT NULL);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
