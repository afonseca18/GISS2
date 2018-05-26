USE master

IF ( EXISTS( SELECT *
FROM [dbo].[sysdatabases]
Where name = 'Projecto') )
Begin
    DROP DATABASE Projecto
end


IF (NOT EXISTS( SELECT *
FROM [dbo].[sysdatabases]
Where name = 'Projecto') )
Begin

    CREATE DATABASE Projecto
  ON 
   ( NAME = 'Projecto_dat',
      FILENAME = 'C:\BD1718\Projectodat.mdf',
      
      SIZE = 10,
      MAXSIZE = 50,
      FILEGROWTH = 5 )
   LOG ON
   ( NAME = 'Projecto_log',
     FILENAME = 'C:\BD1718\Projectolog.ldf',

     SIZE = 5MB,
     MAXSIZE = 25MB,
     FILEGROWTH = 5MB )
end