Use DBProject

INSERT INTO Regioes(Nome) 
Values ('Açores'),
	   ('Alentejo'),
	   ('Algarve'),
	   ('Beira Interior'),
	   ('Centro'),
	   ('Lisboa'),
	   ('Madeira'),
	   ('Norte'),
	   ('Ribatejo'),
	   ('UBI Época de Exames');


INSERT INTO Centro_Hospitalar (Nome,RegioesID)
Values ('Barreiro Montijo',9),
	   ('Cova da Beira',4),
	   ('Central do Funchal',7),
	   ('Leiria Pombal',5),
	   ('Lisboa Ocidental',6),
	   ('São João',8),
	   ('Universitário do Algarve',3),
	   ('Universitário de Coimbra',5),
	   ('Tondela Viseu',5),
	   ('Alto Ave',8);
		

INSERT INTO  Hospitais (Nome,CentroHID)
Values ('Fundão',2),
	   ('Marmeleiros',3),
	   ('São Teotónio',9),
	   ('Senhor Do Bonfim',6),
	   ('Egas Moniz',5),
	   ('Luz',5),
	   ('Pêro Da Covilhã',2),
	   ('Barlavento Algarvio',6),
	   ('Distrital de Santarém',1),
	   ('Dr. José De Almeida',5);

INSERT INTO Centros (Nome,HospitaisID)
Values  ('Centro de Saúde',1),
		('Centro de Internamento',2),
		('Centro de Análises',3),
		('Centro de Tratamento Crónico',4),
		('Centro Operatório',5),
		('Centro de Maternidade',6),
		('Centro Farmacêutico',7),
		('Centro de Urgência',8),
		('Centro de Choro',9),
		('Centro de Exames',10);


INSERT INTO Servicos (Nome,CentrosID)
Values ('Otorrinolaringologia',1),
	   ('Farmaceuticos',2),
	   ('Pediátrico',3),
	   ('Ortopédico',4),
	   ('Cirurgico',5),
	   ('Obstetría',10),
	   ('Bloco',7),
	   ('RoX',8),
	   ('Dermatologia',6),
	   ('Psiquiatrico',9);

INSERT INTO AreasClinicas (Nome,ServicosID)
Values ('Cardiologia',1),
	   ('Pediatria',3),
	   ('Ortopedia',3),
	   ('Urologia',4),
	   ('Fisioterapia',5),
	   ('Dermatologia',6),
	   ('Endocrinologia',7),
	   ('Neurologia',8),
	   ('Psicologia',9),
	   ('Nutrição',10);


INSERT INTO Turnos ( Nome, Hora_Inicial, Hora_Final)
Values ( 'Manhã', 8, 13),
	   ( 'Tarde', 13,19),
	   ( 'Noite', 19,23 ),
	   ( 'Madrugada', 0,5),
	   ( 'Rotativo',5,8),
	   ( 'Folga',1,0),
	   ( 'Especial',0,8),
	   ( 'Escravo', 8,20),
	   ( 'Direto', 1, 0),
	   ( 'Balanço', 20, 0);

INSERT INTO Pacientes ( Nome, Idade)
Values ( 'Carlos António', 45),
	   ( 'Maria Manuela', 27),
	   ( 'David Cage', 48),
	   ( 'Carolina Bicho',  9),
	   ( 'Pedro Batista', 50),
	   ( 'Alexandre Fonseca', 69),
	   ( 'Raquel Guerra', 74),
	   ( 'Joaquim Fernandes', 81),
	   ( 'António Barata', 90),
	   ( 'Luísa Antunes',21);

INSERT INTO PacienteArea (PacienteID, AreasClinicasID)
Values (1,5),
	   (2,5),
       (3,5),
       (4,2),
       (5,5),
       (6,5),
       (7,2),
       (8,2),
       (9,2),
       (10,2);

INSERT INTO PacienteCentroH (PacienteID, CentroHID)
Values (1,3),
	   (2,3),
       (3,3),
       (4,9), 
       (5,3),
       (6,3),
       (7,9), 
       (8,9), 
       (9,9), 
       (10,9); 

INSERT INTO Salas ( Nome, AreasClinicasID)
Values  ('Sala de Operações',1),
		('Sala de Exames',5),
		('Sala de Análises',5),
		('Consultório',5),
		('Enfermaria',5),
		('Sala de Vacinas',6),
		('Sala de Psicologia',7),
		('Sala de Cardiologia',8),
		('Sala de Neurologia',9),
		('Sala de Fisioterapia',10);

INSERT INTO Recursos ( Nome)
Values ('Cama'),
	   ('Penso'),
	   ('Injeção'),
	   ('Soro'),
	   ('Bisturi'),
	   ('Compressa'),
	   ('Máscara'),
	   ('Luva'),
	   ('Carrinho de Limpeza'),
	   ('Estetoscópio');

INSERT INTO RecSalas (RecursosID, SalasID, Disponibilidade, Num_Usos, Numero, Validade)
Values (1,3,1,0,4,20181103),
	   (2,4,2,1,null,20190203),
	   (3,1,2,1,null,20180913),
	   (4,2,4,1,null,20200109),
	   (5,6,5,1,null,20180421),
	   (6,6,1,1,null,20180721),
	   (7,9,7,1,null,20190430),
	   (8,10,8,1,null,20180104),
	   (9,5,9,1,null,20190321),   
	   (10,7,10,1,null,20181031);

INSERT INTO Colaboradores( Nome, Idade)
Values ('Jéssica Antunes', 35),
	   ('Carla Andrade', 58),
	   ('António Jacinto', 42),
	   ('João Martins', 35),
	   ('Inês Soares', 29),
	   ('Rosa Estrela', 61),
	   ('Arménio Ferreira', 28),
	   ('Rui Vitória', 47),
	   ('Hugo Pereira', 41),
	   ('Afonso Jesus', 52);

INSERT INTO ColabArea( ColaboradoresID,AreasClinicasID)
Values (1,2),
	   (2,3),
	   (3,1),
	   (4,5),
	   (5,8),
	   (6,9),
	   (7,10),
	   (8,1),
	   (9,6),
	   (10,7);

INSERT INTO ColabCentroH (ColaboradoresID, CentroHID)
Values (1,3),
	   (2,2),
	   (3,1),
	   (4,7),
	   (5,9),
	   (6,8),
	   (7,6),
	   (8,4),
	   (9,10),
	   (10,5);

INSERT INTO ColaboradoresTurnos (ColaboradoresID, TurnosID, data)
Values (1,10,20180321),
	   (2,9,20180321),
	   (3,8,20180321),
	   (4,7,20180321),
	   (5,6,20180321),
	   (6,5,20180321),
	   (7,4,20180321),
	   (8,3,20180321),
	   (9,2,20180321),
	   (10,1,20180321);

INSERT INTO Especialidades ( Nome)
Values ('Pediatra'),
       ('Enfermeiro'),
	   ('Neurologista'),
	   ('Psicólogo'),
	   ('Cardiologista'),
	   ('Assistente Operacional'),
	   ('Nutricionista'),
	   ('Dermatologista'),
	   ('Ortopedista'),
	   ('Cirurgião');

INSERT INTO ColaboradoresEspecialidades (ColaboradoresID, EspecialidadesID)
Values (1,3),
	   (2,2),
	   (3,1),
	   (4,7),
	   (5,9),
	   (6,8),
	   (7,6),
	   (8,4),
	   (9,10),
	   (10,5);

INSERT INTO TipoIntervencao ( Nome)
Values ( 'Consulta'),
	   ( 'TAC'),
	   ( 'Análise'),
	   ( 'Cirurgia'),
	   ( 'Triagem'),
	   ( 'Fisioterapia'),
	   ( 'Raio-X'),
	   ( 'Ecografia'),
	   ( 'Electrocardiograma'),
	   ( 'Biópsia');

INSERT INTO Intervencao ( AreasClinicasID, SalasID, TipoIntervencaoID, Estado, 
RecursoSalaID, PacienteID, Data, Descricao)
Values ( 10,1,9,0,4,1,20180621,'Consulta de Rotina'),
	   ( 9,2,10,1,3,2,20180622,''),
	   ( 8,3,8,1,2,3,20180623,''),
	   ( 7,4,7,0,5,4,20180624,''),
	   ( 6,5,6,1,4,5,20180625,''),
	   ( 5,6,5,0,8,6,20180626,''),
	   ( 4,7,4,1,9,7,20180627,''),
	   ( 3,8,3,1,2,8,20180628,''),
	   ( 2,9,2,0,3,9,20180629,''),
	   ( 1,10,1,0,7,10,20180630,'');

INSERT INTO Equipa (ColaboradoresID, IntervencaoID)
Values (1,3),
	   (2,2),
	   (3,1),
	   (4,7),
	   (5,9),
	   (6,8),
	   (7,6),
	   (8,4),
	   (9,10),
	   (10,5);



