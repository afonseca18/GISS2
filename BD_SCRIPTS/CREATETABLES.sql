Use DBProject

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Regioes]') )
begin
  CREATE TABLE Regioes(
	  RegioesID int IDENTITY(1,1) NOT NULL 
	     CHECK (RegioesID >= 1),         
    Nome nvarchar(50) NOT NULL, 
    CONSTRAINT PK_RegioesID PRIMARY KEY (RegioesID), 
    CONSTRAINT U_Name UNIQUE (Nome)                    
  ); 
end

-- ............................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Centro_Hospitalar]') )
begin
  CREATE TABLE Centro_Hospitalar (
	  CentroHID int IDENTITY(1,1) NOT NULL 
	     CONSTRAINT PK_CentroHID PRIMARY KEY (CentroHID)
	     CHECK (CentroHID >= 1),
	  Nome nvarchar (50) NOT NULL ,
	  RegioesID int NOT NULL,
	  
	  CONSTRAINT FK_RegioesID FOREIGN KEY (RegioesID) 
	     REFERENCES Regioes(RegioesID)
	     ON UPDATE CASCADE 
	     ON DELETE NO ACTION
  ); 
end

-- ............................................................................
if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Hospitais]') )
begin
  CREATE TABLE Hospitais (
	  HospitaisID int IDENTITY(1,1) NOT NULL
		CONSTRAINT PK_HospitaisID PRIMARY KEY (HospitaisID)
		CHECK (HospitaisID >=1),
	Nome nvarchar (50) NOT NULL,
	CentroHID int NOT NULL,
	CONSTRAINT FK_CentroHID FOREIGN KEY (CentroHID)
		REFERENCES Centro_Hospitalar(CentroHID)
		ON UPDATE CASCADE
		ON DELETE NO ACTION
  );
end
--.....................................................
if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Centros]') )
begin
  CREATE TABLE Centros (
	  CentrosID int IDENTITY(1,1)NOT NULL
		CONSTRAINT PK_CentrosID PRIMARY KEY (CentrosID)
		CHECK (CentrosID >=1),
	Nome nvarchar (50) NOT NULL,
	HospitaisID int NOT NULL,
	CONSTRAINT FK_HospitaisID FOREIGN KEY (HospitaisID)
		REFERENCES Hospitais(HospitaisID)
		ON UPDATE CASCADE
		ON DELETE NO ACTION
  );    
end
-- ............................................................................
if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Servicos]') )
begin
  CREATE TABLE Servicos (
	  ServicosID int IDENTITY(1,1)NOT NULL
		CONSTRAINT PK_ServicosID PRIMARY KEY (ServicosID)
		CHECK (ServicosID >=1),
	Nome nvarchar (50) NOT NULL,
	CentrosID int NOT NULL,
	CONSTRAINT FK_CentrosID FOREIGN KEY (CentrosID)
		REFERENCES Centros(CentrosID)
		ON UPDATE CASCADE
		ON DELETE NO ACTION
  );    
end

--.............................................................................
if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[AreasClinicas]') )
begin
  CREATE TABLE AreasClinicas (
	  AreasClinicasID int IDENTITY(1,1)NOT NULL
		CONSTRAINT PK_AreasClinicasID PRIMARY KEY (AreasClinicasID)
		CHECK (AreasClinicasID >=1),
	Nome nvarchar (50) NOT NULL,
	ServicosID int NOT NULL,
	CONSTRAINT FK_ServicosID FOREIGN KEY (ServicosID)
		REFERENCES Servicos(ServicosID)
		ON UPDATE CASCADE
		ON DELETE NO ACTION
  );    
end

--.....................................................................................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Turnos]') )
begin
  CREATE TABLE Turnos (
	TurnosID int IDENTITY(1,1)NOT NULL,
	Nome nvarchar(50) NOT NULL,
	Hora_Inicial nvarchar(5) not NULL,
	Hora_Final nvarchar(5) not NULL,
		CONSTRAINT PK_TurnosID PRIMARY KEY (TurnosID)
);
end

--............................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Pacientes]') )
begin
  CREATE TABLE Pacientes (
	  PacienteID int IDENTITY(1,1)NOT NULL
		CONSTRAINT PK_PacientesID PRIMARY KEY (PacienteID)
		CHECK (PacienteID >=1),
	Nome nvarchar (50) NOT NULL,
	Idade int ,
	CHECK (Idade >= 0)
  );    
end

--.....................................................................................................................................
if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Salas]') )
begin
  CREATE TABLE Salas (
	  SalasID int IDENTITY(1,1)NOT NULL
		CONSTRAINT PK_SalasID PRIMARY KEY (SalasID)
		CHECK (SalasID >=1),
	Nome nvarchar (50) NOT NULL,
	AreasClinicasID int NOT NULL,
	CONSTRAINT FK_AreasClinicasIDS FOREIGN KEY (AreasClinicasID)
		REFERENCES AreasClinicas(AreasClinicasID)
		ON UPDATE CASCADE
		ON DELETE NO ACTION
  );    
end

--.....................................................................................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Recursos]') )
begin
  CREATE TABLE Recursos (
	  RecursosID int IDENTITY(1,1)NOT NULL
		CONSTRAINT PK_RecursosID PRIMARY KEY (RecursosID)
		CHECK (RecursosID >=1),
	Nome nvarchar (50) NOT NULL, 
  );    
end

--.....................................................................................................................................
if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[PacienteArea]') )
begin
  CREATE TABLE PacienteArea (
	  PacienteID int NOT NULL,
	  AreasClinicasID int NOT NULL,
		CONSTRAINT PK_PacienteAreaID PRIMARY KEY (PacienteID,AreasClinicasID),
		CONSTRAINT FK_Paciente_Area FOREIGN KEY  (AreasClinicasID)
		REFERENCES AreasClinicas(AreasClinicasID)
		ON UPDATE CASCADE
		ON DELETE NO ACTION,
		CONSTRAINT FK_PacienteA_Paciente FOREIGN KEY (PacienteID)
		REFERENCES Pacientes(PacienteID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
  );    
end

--.....................................................................................................................................
if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[PacienteCentroH]') )
begin
  CREATE TABLE PacienteCentroH (
	  PacienteID int NOT NULL,
	  CentroHID int NOT NULL,
		CONSTRAINT PK_PacienteCentroHID PRIMARY KEY (PacienteID,CentroHID),
		CONSTRAINT FK_PacienteC_CentroHID FOREIGN KEY (CentroHID)
		REFERENCES Centro_Hospitalar(CentroHID)
		ON UPDATE CASCADE
		ON DELETE NO ACTION, 
		CONSTRAINT FK_PacienteC_Paciente FOREIGN KEY (PacienteID)
		REFERENCES Pacientes(PacienteID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
  );    
end

--.....................................................................................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[RecSalas]') )
begin
  CREATE TABLE RecSalas (
	RecursoSalaID int IDENTITY(1,1) NOT NULL,
	CONSTRAINT PK_RecSalasID PRIMARY KEY (RecursoSalaID),
	  RecursosID int NOT NULL
		CONSTRAINT FK_RecSalas_Recursos FOREIGN KEY (RecursosID)
		REFERENCES Recursos(RecursosID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	SalasID int NOT NULL,
	CONSTRAINT FK_RecSalas_Salas FOREIGN KEY (SalasID)
		REFERENCES Salas(SalasID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	Disponibilidade int NOT NULL,
	Num_Usos int NOT NULL,
	Numero int,
	Validade bigint NOT NULL
  );    
end

--....................................................................................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Colaboradores]') )
begin
  CREATE TABLE Colaboradores (
	ColaboradoresID int IDENTITY(1,1)NOT NULL,
	Nome nvarchar(50) NOT NULL,
	Idade int ,
	CHECK (Idade >= 0),
		CONSTRAINT PK_ColaboradoresID PRIMARY KEY (ColaboradoresID)
);
end

--.....................................................................................................................................


if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[ColabArea]') )
begin
  CREATE TABLE ColabArea (
	ColaboradoresID int NOT NULL,
	AreasClinicasID int NOT NULL,
		CONSTRAINT PK_ColabAreaID PRIMARY KEY (ColaboradoresID,AreasClinicasID),
		CONSTRAINT FK_ColabArea_Area FOREIGN KEY (AreasClinicasID)
		REFERENCES AreasClinicas(AreasClinicasID)
		ON UPDATE CASCADE
		ON DELETE NO ACTION,
		CONSTRAINT FK_ColabArea_Colab FOREIGN KEY (ColaboradoresID)
		REFERENCES Colaboradores(ColaboradoresID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,

);
end
--.....................................................................................................................................
if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[ColabCentroH]') )
begin
  CREATE TABLE ColabCentroH (
	ColaboradoresID int NOT NULL,
	CentroHID int NOT NULL,
		CONSTRAINT PK_ColabCentroHID PRIMARY KEY (ColaboradoresID,CentroHID),
		CONSTRAINT FK_ColabCentroH_Area FOREIGN KEY (CentroHID)
		REFERENCES Centro_Hospitalar(CentroHID)
		ON UPDATE CASCADE
		ON DELETE NO ACTION,
		CONSTRAINT FK_ColabCentroH_Colab FOREIGN KEY (ColaboradoresID)
		REFERENCES Colaboradores(ColaboradoresID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,

);
end
--.....................................................................................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[ColaboradoresTurnos]') )
begin
  CREATE TABLE ColaboradoresTurnos (
	ColaboradoresID int NOT NULL,
	TurnosID int NOT NULL,
	data bigint NOT NULL,
		CONSTRAINT PK_ColaboradoresTurnosID PRIMARY KEY (ColaboradoresID,TurnosID),
		CONSTRAINT FK_CT_Colaboradores FOREIGN KEY (ColaboradoresID)
		REFERENCES Colaboradores(ColaboradoresID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
		CONSTRAINT FK_CT_Turnos FOREIGN KEY (TurnosID)
		REFERENCES Turnos(TurnosID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
end


--.....................................................................................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Especialidades]') )
begin
  CREATE TABLE Especialidades (
	EspecialidadesID int IDENTITY(1,1)NOT NULL,
	Nome nvarchar(50) NOT NULL,
		CONSTRAINT PK_EspecialidadesID PRIMARY KEY (EspecialidadesID)
);
end

--.....................................................................................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[ColaboradoresEspecialidades]') )
begin
  CREATE TABLE ColaboradoresEspecialidades (
	ColaboradoresID int NOT NULL,
	EspecialidadesID int NOT NULL,
		CONSTRAINT PK_ColaboradoresEspecialidadesID PRIMARY KEY (ColaboradoresID,EspecialidadesID),
		CONSTRAINT FK_CE_Colaboradores FOREIGN KEY (ColaboradoresID)
		REFERENCES Colaboradores(ColaboradoresID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
		CONSTRAINT FK_CE_Especialidades FOREIGN KEY (EspecialidadesID)
		REFERENCES Especialidades(EspecialidadesID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
);
end


--.....................................................................................................................................
if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[TipoIntervencao]') )
begin
  CREATE TABLE TipoIntervencao (
	TipoIntervencaoID int IDENTITY(1,1) NOT NULL,
	Nome nvarchar(50) NOT NULL,
		CONSTRAINT PK_TipoIntervencaoID PRIMARY KEY (TipoIntervencaoID)
);
end

--.....................................................................................................................................
if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Intervencao]') )
begin
  CREATE TABLE Intervencao (
	IntervencaoID int IDENTITY(1,1)NOT NULL,
	AreasClinicasID int NOT NULL,
	SalasID int NOT NULL,
	TipoIntervencaoID int NOT NULL,
	Estado int NOT NULL,
	RecursoSalaID int,
	PacienteID int NOT NULL,
	Data bigint NOT NULL,
	Descricao nvarchar(500),
		CONSTRAINT PK_IntervencaoID PRIMARY KEY (IntervencaoID),
		CONSTRAINT FK_AreaClinicaIID FOREIGN KEY (AreasClinicasID)
			REFERENCES AreasClinicas(AreasClinicasID)
			ON UPDATE CASCADE
			ON DELETE NO ACTION,
		CONSTRAINT FK_TipoIntervencaoIID FOREIGN KEY (TipoIntervencaoID)
			REFERENCES TipoIntervencao(TipoIntervencaoID)
			ON UPDATE CASCADE
			ON DELETE NO ACTION,
);
end
--.....................................................................................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Equipa]') )
begin
  CREATE TABLE Equipa (
	ColaboradoresID int NOT NULL,
	IntervencaoID int NOT NULL,
		CONSTRAINT PK_EquipaID PRIMARY KEY (ColaboradoresID,IntervencaoID),
		CONSTRAINT FK_Intervencao_Corpo FOREIGN KEY (IntervencaoID)
		REFERENCES Intervencao(IntervencaoID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
		CONSTRAINT FK_Colaboradores_Corpo FOREIGN KEY (ColaboradoresID)
		REFERENCES Colaboradores(ColaboradoresID)
		ON UPDATE CASCADE
		ON DELETE NO ACTION


);
end
--.....................................................................................................................................

