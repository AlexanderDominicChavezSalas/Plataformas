--
-- Archivo generado con SQLiteStudio v3.4.4 el jue. Feb. 8 11:35:42 2024
--
-- Codificación de texto usada: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Tabla: Medicina
CREATE TABLE IF NOT EXISTS Medicina (MedCod INTEGER PRIMARY KEY AUTOINCREMENT, MedNom TEXT NOT NULL, MedDes TEXT, UniMedCod TEXT CONSTRAINT UniMedCod REFERENCES UnidadMedicina (UniMedCod) NOT NULL, MedDos NUMERIC NOT NULL, MedNiv INTEGER NOT NULL, MedCon INTEGER NOT NULL DEFAULT (1), MedEstReg TEXT NOT NULL);
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (1, 'Penicilina', 'Demo', '2', 123, 1, 0, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (2, 'Penicilina', 'Demo', '8', 109, 1, 1, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (3, 'Penicilina', 'Demo', '9', 101, 1, 2, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (4, 'Penicilina', 'Demo', '5', 114, 3, 3, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (5, 'Penicilina', 'Demo', '9', 122, 3, 6, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (6, 'Penicilina', 'Demo', '9', 110, 1, 4, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (7, 'Penicilina', 'Demo', '9', 122, 3, 3, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (8, 'Penicilina', 'Demo', '9', 104, 2, 0, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (9, 'Penicilina', 'Demo', '6', 122, 3, 0, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (10, 'Penicilina', 'Demo', '8', 118, 2, 3, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (11, 'Paracetamol', 'Demo', '9', 104, 2, 2, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (12, 'Paracetamol', 'Demo', '1', 102, 3, 0, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (13, 'Paracetamol', 'Demo', '1', 110, 2, 3, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (14, 'Paracetamol', 'Demo', '8', 110, 2, 0, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (15, 'Paracetamol', 'Demo', '7', 103, 1, 4, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (16, 'Paracetamol', 'Demo', '1', 118, 2, 5, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (17, 'Paracetamol', 'Demo', '4', 110, 2, 6, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (18, 'Paracetamol', 'Demo', '9', 116, 3, 0, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (19, 'Paracetamol', 'Demo', '1', 120, 3, 6, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (20, 'Paracetamol', 'No set', '5', 105, 3, 0, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (21, 'Paracetamol', 'Demo', '9', 110, 2, 7, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (22, 'Paracetamol', 'Demo', '3', 108, 1, 8, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (23, 'Paracetamol', 'Demo', '5', 100, 1, 12, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (24, 'Paracetamol', 'Demo', '8', 102, 2, 18, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (25, 'Paracetamol', 'Demo', '5', 119, 2, 18, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (26, 'Paracetamol', 'Demo', '3', 106, 3, 3, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (27, 'Paracetamol', 'Demo', '7', 112, 1, 13, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (28, 'Paracetamol', 'Demo', '6', 119, 1, 3, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (29, 'Paracetamol', 'Demo', '7', 100, 2, 14, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (30, 'Paracetamol', 'Demo', '9', 121, 2, 11, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (31, 'Ibuprofeno', 'Demo', '1', 114, 1, 10, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (32, 'Ibuprofeno', 'Demo', '1', 107, 2, 10, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (33, 'Ibuprofeno', 'Demo', '8', 125, 3, 17, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (34, 'Ibuprofeno', 'Demo', '2', 110, 2, 4, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (35, 'Ibuprofeno', 'Demo', '5', 123, 2, 5, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (36, 'Ibuprofeno', 'Demo', '2', 108, 2, 11, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (37, 'Ibuprofeno', 'Demo', '2', 121, 3, 9, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (38, 'Ibuprofeno', 'Demo', '4', 120, 3, 1, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (39, 'Ibuprofeno', 'Demo', '4', 114, 1, 10, 'A');
INSERT INTO Medicina (MedCod, MedNom, MedDes, UniMedCod, MedDos, MedNiv, MedCon, MedEstReg) VALUES (40, 'Ibuprofeno', 'Demo', '1', 108, 2, 19, 'A');

-- Tabla: Recordatorio
CREATE TABLE IF NOT EXISTS Recordatorio (RecCod INTEGER PRIMARY KEY AUTOINCREMENT, MedCod INTEGER CONSTRAINT MedCod REFERENCES Medicina (MedCod) NOT NULL, RecFre INTEGER NOT NULL, RecFecIni TEXT NOT NULL, RecFecFin TEXT NOT NULL, RecEstReg TEXT NOT NULL DEFAULT A);
INSERT INTO Recordatorio (RecCod, MedCod, RecFre, RecFecIni, RecFecFin, RecEstReg) VALUES (1, 16, 18, '07-02-2024 11:20:00', '07-02-2024 11:23:00', 'A');
INSERT INTO Recordatorio (RecCod, MedCod, RecFre, RecFecIni, RecFecFin, RecEstReg) VALUES (2, 15, 24, '07-02-2024 11:20:00', '07-02-2024 11:23:00', 'A');
INSERT INTO Recordatorio (RecCod, MedCod, RecFre, RecFecIni, RecFecFin, RecEstReg) VALUES (3, 34, 22, '07-02-2024 11:20:00', '07-02-2024 11:23:00', 'A');
INSERT INTO Recordatorio (RecCod, MedCod, RecFre, RecFecIni, RecFecFin, RecEstReg) VALUES (4, 35, 15, '07-02-2024 11:20:00', '07-02-2024 11:23:00', 'A');
INSERT INTO Recordatorio (RecCod, MedCod, RecFre, RecFecIni, RecFecFin, RecEstReg) VALUES (5, 18, 14, '07-02-2024 11:20:00', '07-02-2024 11:23:00', 'A');
INSERT INTO Recordatorio (RecCod, MedCod, RecFre, RecFecIni, RecFecFin, RecEstReg) VALUES (6, 31, 23, '07-02-2024 11:20:00', '07-02-2024 11:23:00', 'A');
INSERT INTO Recordatorio (RecCod, MedCod, RecFre, RecFecIni, RecFecFin, RecEstReg) VALUES (7, 6, 10, '07-02-2024 11:20:00', '07-02-2024 11:23:00', 'A');
INSERT INTO Recordatorio (RecCod, MedCod, RecFre, RecFecIni, RecFecFin, RecEstReg) VALUES (8, 38, 8, '07-02-2024 11:20:00', '07-02-2024 11:23:00', 'A');
INSERT INTO Recordatorio (RecCod, MedCod, RecFre, RecFecIni, RecFecFin, RecEstReg) VALUES (9, 33, 15, '07-02-2024 11:20:00', '07-02-2024 11:23:00', 'A');
INSERT INTO Recordatorio (RecCod, MedCod, RecFre, RecFecIni, RecFecFin, RecEstReg) VALUES (10, 11, 7, '07-02-2024 11:20:00', '07-02-2024 11:23:00', 'A');
INSERT INTO Recordatorio (RecCod, MedCod, RecFre, RecFecIni, RecFecFin, RecEstReg) VALUES (11, 33, 12, '07-02-2024 11:20:00', '07-02-2024 11:23:00', 'A');
INSERT INTO Recordatorio (RecCod, MedCod, RecFre, RecFecIni, RecFecFin, RecEstReg) VALUES (12, 4, 9, '07-02-2024 11:20:00', '07-02-2024 11:23:00', 'A');
INSERT INTO Recordatorio (RecCod, MedCod, RecFre, RecFecIni, RecFecFin, RecEstReg) VALUES (13, 28, 22, '07-02-2024 11:20:00', '07-02-2024 11:23:00', 'A');
INSERT INTO Recordatorio (RecCod, MedCod, RecFre, RecFecIni, RecFecFin, RecEstReg) VALUES (14, 8, 12, '07-02-2024 11:20:00', '07-02-2024 11:23:00', 'A');
INSERT INTO Recordatorio (RecCod, MedCod, RecFre, RecFecIni, RecFecFin, RecEstReg) VALUES (15, 14, 8, '07-02-2024 11:20:00', '07-02-2024 11:23:00', 'A');

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
