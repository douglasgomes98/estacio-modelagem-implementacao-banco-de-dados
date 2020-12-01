package com.example.modelagem.startup;

import com.example.modelagem.entidades.*;
import com.example.modelagem.repositorios.*;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Configuration
public class Start implements CommandLineRunner {

    @Autowired
    PessoaRepositorio pessoaRepositorio;

    @Autowired
    CargoRepositorio cargoRepositorio;

    @Autowired
    FuncionarioRepositorio funcionarioRepositorio;

    @Autowired
    RegimeTrabalhoRepositorio regimeTrabalhoRepositorio;

    @Autowired
    TitulacaoRepositorio titulacaoRepositorio;

    @Autowired
    ProfessorRepositorio professorRepositorio;

    @Autowired
    InstituicaoRepositorio instituicaoRepositorio;

    @Autowired
    DisciplinaRepositorio disciplinaRepositorio;

    @Autowired
    ModalidadeCursoRepositorio modalidadeCursoRepositorio;

    @Autowired
    StatusCursoRepositorio statusCursoRepositorio;

    @Autowired
    GrauCursoRepositorio grauCursoRepositorio;

    @Autowired
    MatrizRepositorio matrizRepositorio;

    @Autowired
    CursoRepositorio cursoRepositorio;

    @Autowired
    ModalidadeDisciplinaRepositorio modalidadeDisciplinaRepositorio;

    @Autowired
    ProfessorDisciplinaRepositorio professorDisciplinaRepositorio;

    @Autowired
    CursoDisciplinaRepositorio cursoDisciplinaRepositorio;

    @Autowired
    ClassificacaoCursoRepositorio classificacaoCursoRepositorio;

    @Override
    public void run(String... args) throws Exception {

    }

    private void insertProfessores() throws Exception {
        File file = new File("E:\\OneDrive - Educacional\\Faculdade\\Trabalhos da Faculdade\\6 Semestre\\Implementação de Banco de Dados\\Docentes.xlsx");
        OPCPackage pkg = OPCPackage.open(file);
        XSSFWorkbook wb = new XSSFWorkbook(pkg);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;

        int rows;
        rows = sheet.getPhysicalNumberOfRows();

        int cols = 0; // No of columns
        int tmp = 0;

        // This trick ensures that we get the data properly even if it doesn't start from first few rows
        for (int i = 0; i < 10 || i < rows; i++) {
            row = sheet.getRow(i);
            if (row != null) {
                tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                if (tmp > cols) cols = tmp;
            }
        }

        for (int r = 0; r < rows; r++) {
            row = sheet.getRow(r);
            if (row != null && r > 0) {
                String nome = row.getCell(0).getStringCellValue();
//                System.out.println(nome);
                String cpf = row.getCell(1).getRawValue();
//                System.out.println(cpf);
                Double experienciaProfissional = row.getCell(9).getNumericCellValue();
//                System.out.println(experienciaProfissional.intValue());
                Double experienciaSuperior = row.getCell(10).getNumericCellValue();
//                System.out.println(experienciaSuperior.intValue());
                Double publicacoes = row.getCell(11).getNumericCellValue();
//                System.out.println(publicacoes.intValue());
                String titulacao = row.getCell(4).getStringCellValue();
//                System.out.println(titulacao);
                String regime = row.getCell(5).getStringCellValue();
//                System.out.println(regime);

                Pessoa pessoa = new Pessoa(null, cpf, nome.toUpperCase());
                pessoaRepositorio.save(pessoa);
                Optional<Cargo> cargo = cargoRepositorio.findById(1);
                Funcionario funcionario = Funcionario.builder()
                        .cargo(cargo.get())
                        .pessoa(pessoa)
                        .build();
                funcionarioRepositorio.save(funcionario);

                Optional<RegimeTrabalho> regimeTrabalho = regimeTrabalhoRepositorio.findByNome(regime.toUpperCase());

                if (!regimeTrabalho.isPresent()) {
                    regimeTrabalhoRepositorio.save(new RegimeTrabalho(null, regime.toUpperCase()));
                    regimeTrabalho = regimeTrabalhoRepositorio.findByNome(regime.toUpperCase());
                }

                Optional<Titulacao> titulacaoOptional = titulacaoRepositorio.findByNome(titulacao.toUpperCase());

                if (!titulacaoOptional.isPresent()) {
                    titulacaoRepositorio.save(new Titulacao(null, titulacao.toUpperCase()));
                    titulacaoOptional = titulacaoRepositorio.findByNome(titulacao.toUpperCase());
                }

                Professor professor = Professor.builder()
                        .experienciaForaMagisterioSuperior(experienciaProfissional.intValue())
                        .experienciaProfissionalMagisterioSuperior(experienciaSuperior.intValue())
                        .quantidadePublicacao(publicacoes.intValue())
                        .funcionario(funcionario)
                        .regimeTrabalho(regimeTrabalho.get())
                        .titulacao(titulacaoOptional.get())
                        .build();

                professorRepositorio.save(professor);
            }
        }
    }

    private void insertDisciplinas() throws Exception {
        File file = new File("E:\\OneDrive - Educacional\\Faculdade\\Trabalhos da Faculdade\\6 Semestre\\Implementação de Banco de Dados\\Professores x disciplina.xlsx");
        OPCPackage pkg = OPCPackage.open(file);
        XSSFWorkbook wb = new XSSFWorkbook(pkg);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;

        int rows;
        rows = sheet.getPhysicalNumberOfRows();

        int cols = 0; // No of columns
        int tmp = 0;

        // This trick ensures that we get the data properly even if it doesn't start from first few rows
        for (int i = 0; i < 10 || i < rows; i++) {
            row = sheet.getRow(i);
            if (row != null) {
                tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                if (tmp > cols) cols = tmp;
            }
        }

        for (int r = 0; r < rows; r++) {
            row = sheet.getRow(r);
            if (row != null && r > 0) {
                String cod = row.getCell(8).getStringCellValue();
//                System.out.println(cod);
                String nome = row.getCell(9).getStringCellValue();
//                System.out.println(nome);
                String professor = row.getCell(7).getStringCellValue();

                Optional<Disciplina> optionalDisciplina = disciplinaRepositorio.findByCodigo(cod);
                Optional<Professor> optionalProfessor = professorRepositorio.findByFuncionarioPessoaNome(professor);

                if (!optionalDisciplina.isPresent()) {
                    Disciplina disciplina = Disciplina.builder()
                            .codigo(cod)
                            .nome(nome)
                            .build();

                    disciplina = disciplinaRepositorio.save(disciplina);

                    if (optionalProfessor.isPresent()) {
                        ProfessorDisciplinaPK professorDisciplinaPK = new ProfessorDisciplinaPK(optionalProfessor.get(), disciplina);
                        ProfessorDisciplina professorDisciplina = new ProfessorDisciplina(professorDisciplinaPK);
                        professorDisciplinaRepositorio.save(professorDisciplina);
                    }

                } else {
                    if (optionalProfessor.isPresent()) {
                        ProfessorDisciplinaPK professorDisciplinaPK = new ProfessorDisciplinaPK(optionalProfessor.get(), optionalDisciplina.get());
                        ProfessorDisciplina professorDisciplina = new ProfessorDisciplina(professorDisciplinaPK);
                        professorDisciplinaRepositorio.save(professorDisciplina);
                    }
                }
            }
        }
    }

    private void insertDisciplinaTI116() throws Exception {
        File file = new File("E:\\OneDrive - Educacional\\Faculdade\\Trabalhos da Faculdade\\6 Semestre\\Implementação de Banco de Dados\\Matriz116 Formatada.xlsx");
        OPCPackage pkg = OPCPackage.open(file);
        XSSFWorkbook wb = new XSSFWorkbook(pkg);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;

        int rows;
        rows = sheet.getPhysicalNumberOfRows();

        int cols = 0; // No of columns
        int tmp = 0;

        // This trick ensures that we get the data properly even if it doesn't start from first few rows
        for (int i = 0; i < 10 || i < rows; i++) {
            row = sheet.getRow(i);
            if (row != null) {
                tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                if (tmp > cols) cols = tmp;
            }
        }

        Curso optionalCurso = cursoRepositorio.findById(1).get();
        ModalidadeDisciplina optionalModalidadeDisciplinaPresencial = modalidadeDisciplinaRepositorio.findById(1).get();
        ModalidadeDisciplina optionalModalidadeDisciplinaEad = modalidadeDisciplinaRepositorio.findById(2).get();
        ModalidadeDisciplina optionalModalidadeDisciplinaMista = modalidadeDisciplinaRepositorio.findById(3).get();

        for (int r = 0; r < rows; r++) {
            row = sheet.getRow(r);
            if (row != null && r > 0) {

                String codDisciplina = row.getCell(1).getStringCellValue();
//                System.out.println(codDisciplina);

                Optional<Disciplina> optionalDisciplina = disciplinaRepositorio.findByCodigo(codDisciplina);

                if (optionalDisciplina.isPresent()) {
                    Double periodo = row.getCell(0).getNumericCellValue();
//                System.out.println(periodo);
                    optionalDisciplina.get().setPeriodoMinimo(periodo.intValue());

                    Double cargaHoraria = row.getCell(2).getNumericCellValue();
//                System.out.println(cargaHoraria);
                    optionalDisciplina.get().setCargaHoraria(cargaHoraria.intValue());

                    String modalidade = row.getCell(3).getStringCellValue();
//                System.out.println(modalidade);

                    if (modalidade.equals("PRESENCIAL")) {
                        optionalDisciplina.get().setModalidadeDisciplina(optionalModalidadeDisciplinaPresencial);
                    } else if (modalidade.equals("ONLINE (EAD)")) {
                        optionalDisciplina.get().setModalidadeDisciplina(optionalModalidadeDisciplinaEad);
                    } else if (modalidade.equals("MISTA (PRES. + EAD)")) {
                        optionalDisciplina.get().setModalidadeDisciplina(optionalModalidadeDisciplinaMista);
                    }

                    try {
                        Double vagasAutorizadas = row.getCell(5).getNumericCellValue();
//                        System.out.println(vagasAutorizadas);
                        optionalDisciplina.get().setQuantidadeVagaAutorizada(vagasAutorizadas.intValue());
                    } catch (Exception e) {

                    }

                    disciplinaRepositorio.save(optionalDisciplina.get());

                    String classificacao = row.getCell(4).getStringCellValue();

                    Optional<ClassificacaoCurso> optionalClassificacaoCurso = classificacaoCursoRepositorio.findByNome(classificacao);

                    if (!optionalClassificacaoCurso.isPresent()) {
                        classificacaoCursoRepositorio.save(new ClassificacaoCurso(null, classificacao));
                        ClassificacaoCurso classificacaoCurso = classificacaoCursoRepositorio.findByNome(classificacao).get();
                        CursoDisciplinaPK cursoDisciplinaPK = new CursoDisciplinaPK(optionalCurso, optionalDisciplina.get());
                        CursoDisciplina cursoDisciplina = new CursoDisciplina(cursoDisciplinaPK, classificacaoCurso);
                        cursoDisciplinaRepositorio.save(cursoDisciplina);
                    } else {
                        CursoDisciplinaPK cursoDisciplinaPK = new CursoDisciplinaPK(optionalCurso, optionalDisciplina.get());
                        CursoDisciplina cursoDisciplina = new CursoDisciplina(cursoDisciplinaPK, optionalClassificacaoCurso.get());
                        cursoDisciplinaRepositorio.save(cursoDisciplina);
                    }

                }

            }
        }
    }

    private void insertDisciplinaTI118() throws Exception {
        File file = new File("E:\\OneDrive - Educacional\\Faculdade\\Trabalhos da Faculdade\\6 Semestre\\Implementação de Banco de Dados\\118_Sistemas_de_informacao_Formatada.xlsx");
        OPCPackage pkg = OPCPackage.open(file);
        XSSFWorkbook wb = new XSSFWorkbook(pkg);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;

        int rows;
        rows = sheet.getPhysicalNumberOfRows();

        int cols = 0; // No of columns
        int tmp = 0;

        // This trick ensures that we get the data properly even if it doesn't start from first few rows
        for (int i = 0; i < 10 || i < rows; i++) {
            row = sheet.getRow(i);
            if (row != null) {
                tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                if (tmp > cols) cols = tmp;
            }
        }

        Curso optionalCurso = cursoRepositorio.findById(2).get();
        ModalidadeDisciplina optionalModalidadeDisciplinaPresencial = modalidadeDisciplinaRepositorio.findById(1).get();
        ModalidadeDisciplina optionalModalidadeDisciplinaEad = modalidadeDisciplinaRepositorio.findById(2).get();
        ModalidadeDisciplina optionalModalidadeDisciplinaMista = modalidadeDisciplinaRepositorio.findById(3).get();

        for (int r = 0; r < rows; r++) {
            row = sheet.getRow(r);
            if (row != null && r > 0) {

                String codDisciplina = row.getCell(1).getStringCellValue();
//                System.out.println(codDisciplina);

                Optional<Disciplina> optionalDisciplina = disciplinaRepositorio.findByCodigo(codDisciplina);

                if (optionalDisciplina.isPresent()) {
                    Double periodo = row.getCell(0).getNumericCellValue();
//                System.out.println(periodo);
                    optionalDisciplina.get().setPeriodoMinimo(periodo.intValue());

                    Double cargaHoraria = row.getCell(2).getNumericCellValue();
//                System.out.println(cargaHoraria);
                    optionalDisciplina.get().setCargaHoraria(cargaHoraria.intValue());

                    String modalidade = row.getCell(3).getStringCellValue();
//                System.out.println(modalidade);

                    if (modalidade.equals("PRESENCIAL")) {
                        optionalDisciplina.get().setModalidadeDisciplina(optionalModalidadeDisciplinaPresencial);
                    } else if (modalidade.equals("ONLINE (EAD)")) {
                        optionalDisciplina.get().setModalidadeDisciplina(optionalModalidadeDisciplinaEad);
                    } else if (modalidade.equals("MISTA (PRES. + EAD)")) {
                        optionalDisciplina.get().setModalidadeDisciplina(optionalModalidadeDisciplinaMista);
                    }

                    try {
                        Double vagasAutorizadas = row.getCell(5).getNumericCellValue();
//                        System.out.println(vagasAutorizadas);
                        optionalDisciplina.get().setQuantidadeVagaAutorizada(vagasAutorizadas.intValue());
                    } catch (Exception e) {

                    }

                    disciplinaRepositorio.save(optionalDisciplina.get());

                    String classificacao = row.getCell(4).getStringCellValue();

                    Optional<ClassificacaoCurso> optionalClassificacaoCurso = classificacaoCursoRepositorio.findByNome(classificacao);

                    if (!optionalClassificacaoCurso.isPresent()) {
                        classificacaoCursoRepositorio.save(new ClassificacaoCurso(null, classificacao));
                        ClassificacaoCurso classificacaoCurso = classificacaoCursoRepositorio.findByNome(classificacao).get();
                        CursoDisciplinaPK cursoDisciplinaPK = new CursoDisciplinaPK(optionalCurso, optionalDisciplina.get());
                        CursoDisciplina cursoDisciplina = new CursoDisciplina(cursoDisciplinaPK, classificacaoCurso);
                        cursoDisciplinaRepositorio.save(cursoDisciplina);
                    } else {
                        CursoDisciplinaPK cursoDisciplinaPK = new CursoDisciplinaPK(optionalCurso, optionalDisciplina.get());
                        CursoDisciplina cursoDisciplina = new CursoDisciplina(cursoDisciplinaPK, optionalClassificacaoCurso.get());
                        cursoDisciplinaRepositorio.save(cursoDisciplina);
                    }

                }

            }
        }
    }

    private void insertDisciplinaEnfermagem118() throws Exception {
        File file = new File("E:\\OneDrive - Educacional\\Faculdade\\Trabalhos da Faculdade\\6 Semestre\\Implementação de Banco de Dados\\118_Enfermagem_Formatada.xlsx");
        OPCPackage pkg = OPCPackage.open(file);
        XSSFWorkbook wb = new XSSFWorkbook(pkg);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;

        int rows;
        rows = sheet.getPhysicalNumberOfRows();

        int cols = 0; // No of columns
        int tmp = 0;

        // This trick ensures that we get the data properly even if it doesn't start from first few rows
        for (int i = 0; i < 10 || i < rows; i++) {
            row = sheet.getRow(i);
            if (row != null) {
                tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                if (tmp > cols) cols = tmp;
            }
        }

        Curso optionalCurso = cursoRepositorio.findById(3).get();
        ModalidadeDisciplina optionalModalidadeDisciplinaPresencial = modalidadeDisciplinaRepositorio.findById(1).get();
        ModalidadeDisciplina optionalModalidadeDisciplinaEad = modalidadeDisciplinaRepositorio.findById(2).get();
        ModalidadeDisciplina optionalModalidadeDisciplinaMista = modalidadeDisciplinaRepositorio.findById(3).get();

        for (int r = 0; r < rows; r++) {
            row = sheet.getRow(r);
            if (row != null && r > 0) {

                String codDisciplina = row.getCell(1).getStringCellValue();
//                System.out.println(codDisciplina);

                Optional<Disciplina> optionalDisciplina = disciplinaRepositorio.findByCodigo(codDisciplina);

                if (optionalDisciplina.isPresent()) {
                    Double periodo = row.getCell(0).getNumericCellValue();
//                System.out.println(periodo);
                    optionalDisciplina.get().setPeriodoMinimo(periodo.intValue());

                    Double cargaHoraria = row.getCell(2).getNumericCellValue();
//                System.out.println(cargaHoraria);
                    optionalDisciplina.get().setCargaHoraria(cargaHoraria.intValue());

                    String modalidade = row.getCell(3).getStringCellValue();
//                System.out.println(modalidade);

                    if (modalidade.equals("PRESENCIAL")) {
                        optionalDisciplina.get().setModalidadeDisciplina(optionalModalidadeDisciplinaPresencial);
                    } else if (modalidade.equals("ONLINE (EAD)")) {
                        optionalDisciplina.get().setModalidadeDisciplina(optionalModalidadeDisciplinaEad);
                    } else if (modalidade.equals("MISTA (PRES. + EAD)")) {
                        optionalDisciplina.get().setModalidadeDisciplina(optionalModalidadeDisciplinaMista);
                    }

                    try {
                        Double vagasAutorizadas = row.getCell(5).getNumericCellValue();
//                        System.out.println(vagasAutorizadas);
                        optionalDisciplina.get().setQuantidadeVagaAutorizada(vagasAutorizadas.intValue());
                    } catch (Exception e) {

                    }

                    disciplinaRepositorio.save(optionalDisciplina.get());

                    String classificacao = row.getCell(4).getStringCellValue();

                    Optional<ClassificacaoCurso> optionalClassificacaoCurso = classificacaoCursoRepositorio.findByNome(classificacao);

                    if (!optionalClassificacaoCurso.isPresent()) {
                        classificacaoCursoRepositorio.save(new ClassificacaoCurso(null, classificacao));
                        ClassificacaoCurso classificacaoCurso = classificacaoCursoRepositorio.findByNome(classificacao).get();
                        CursoDisciplinaPK cursoDisciplinaPK = new CursoDisciplinaPK(optionalCurso, optionalDisciplina.get());
                        CursoDisciplina cursoDisciplina = new CursoDisciplina(cursoDisciplinaPK, classificacaoCurso);
                        cursoDisciplinaRepositorio.save(cursoDisciplina);
                    } else {
                        CursoDisciplinaPK cursoDisciplinaPK = new CursoDisciplinaPK(optionalCurso, optionalDisciplina.get());
                        CursoDisciplina cursoDisciplina = new CursoDisciplina(cursoDisciplinaPK, optionalClassificacaoCurso.get());
                        cursoDisciplinaRepositorio.save(cursoDisciplina);
                    }

                }

            }
        }
    }

    private void insertDisciplinaDireito118() throws Exception {
        File file = new File("E:\\OneDrive - Educacional\\Faculdade\\Trabalhos da Faculdade\\6 Semestre\\Implementação de Banco de Dados\\118_Direito_Formatada.xlsx");
        OPCPackage pkg = OPCPackage.open(file);
        XSSFWorkbook wb = new XSSFWorkbook(pkg);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;

        int rows;
        rows = sheet.getPhysicalNumberOfRows();

        int cols = 0; // No of columns
        int tmp = 0;

        // This trick ensures that we get the data properly even if it doesn't start from first few rows
        for (int i = 0; i < 10 || i < rows; i++) {
            row = sheet.getRow(i);
            if (row != null) {
                tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                if (tmp > cols) cols = tmp;
            }
        }

        Curso optionalCurso = cursoRepositorio.findById(4).get();
        ModalidadeDisciplina optionalModalidadeDisciplinaPresencial = modalidadeDisciplinaRepositorio.findById(1).get();
        ModalidadeDisciplina optionalModalidadeDisciplinaEad = modalidadeDisciplinaRepositorio.findById(2).get();
        ModalidadeDisciplina optionalModalidadeDisciplinaMista = modalidadeDisciplinaRepositorio.findById(3).get();

        for (int r = 0; r < rows; r++) {
            row = sheet.getRow(r);
            if (row != null && r > 0) {

                String codDisciplina = row.getCell(1).getStringCellValue();
//                System.out.println(codDisciplina);

                Optional<Disciplina> optionalDisciplina = disciplinaRepositorio.findByCodigo(codDisciplina);

                if (optionalDisciplina.isPresent()) {
                    Double periodo = row.getCell(0).getNumericCellValue();
//                System.out.println(periodo);
                    optionalDisciplina.get().setPeriodoMinimo(periodo.intValue());

                    Double cargaHoraria = row.getCell(2).getNumericCellValue();
//                System.out.println(cargaHoraria);
                    optionalDisciplina.get().setCargaHoraria(cargaHoraria.intValue());

                    String modalidade = row.getCell(3).getStringCellValue();
//                System.out.println(modalidade);

                    if (modalidade.equals("PRESENCIAL")) {
                        optionalDisciplina.get().setModalidadeDisciplina(optionalModalidadeDisciplinaPresencial);
                    } else if (modalidade.equals("ONLINE (EAD)")) {
                        optionalDisciplina.get().setModalidadeDisciplina(optionalModalidadeDisciplinaEad);
                    } else if (modalidade.equals("MISTA (PRES. + EAD)")) {
                        optionalDisciplina.get().setModalidadeDisciplina(optionalModalidadeDisciplinaMista);
                    }

                    try {
                        Double vagasAutorizadas = row.getCell(5).getNumericCellValue();
//                        System.out.println(vagasAutorizadas);
                        optionalDisciplina.get().setQuantidadeVagaAutorizada(vagasAutorizadas.intValue());
                    } catch (Exception e) {

                    }

                    disciplinaRepositorio.save(optionalDisciplina.get());

                    String classificacao = row.getCell(4).getStringCellValue();

                    Optional<ClassificacaoCurso> optionalClassificacaoCurso = classificacaoCursoRepositorio.findByNome(classificacao);

                    if (!optionalClassificacaoCurso.isPresent()) {
                        classificacaoCursoRepositorio.save(new ClassificacaoCurso(null, classificacao));
                        ClassificacaoCurso classificacaoCurso = classificacaoCursoRepositorio.findByNome(classificacao).get();
                        CursoDisciplinaPK cursoDisciplinaPK = new CursoDisciplinaPK(optionalCurso, optionalDisciplina.get());
                        CursoDisciplina cursoDisciplina = new CursoDisciplina(cursoDisciplinaPK, classificacaoCurso);
                        cursoDisciplinaRepositorio.save(cursoDisciplina);
                    } else {
                        CursoDisciplinaPK cursoDisciplinaPK = new CursoDisciplinaPK(optionalCurso, optionalDisciplina.get());
                        CursoDisciplina cursoDisciplina = new CursoDisciplina(cursoDisciplinaPK, optionalClassificacaoCurso.get());
                        cursoDisciplinaRepositorio.save(cursoDisciplina);
                    }

                }

            }
        }
    }

    private void insertDisciplinaEngenhariaCivil118() throws Exception {
        File file = new File("E:\\OneDrive - Educacional\\Faculdade\\Trabalhos da Faculdade\\6 Semestre\\Implementação de Banco de Dados\\118_Civilformatada.xlsx");
        OPCPackage pkg = OPCPackage.open(file);
        XSSFWorkbook wb = new XSSFWorkbook(pkg);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;

        int rows;
        rows = sheet.getPhysicalNumberOfRows();

        int cols = 0; // No of columns
        int tmp = 0;

        // This trick ensures that we get the data properly even if it doesn't start from first few rows
        for (int i = 0; i < 10 || i < rows; i++) {
            row = sheet.getRow(i);
            if (row != null) {
                tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                if (tmp > cols) cols = tmp;
            }
        }

        Curso optionalCurso = cursoRepositorio.findById(5).get();
        ModalidadeDisciplina optionalModalidadeDisciplinaPresencial = modalidadeDisciplinaRepositorio.findById(1).get();
        ModalidadeDisciplina optionalModalidadeDisciplinaEad = modalidadeDisciplinaRepositorio.findById(2).get();
        ModalidadeDisciplina optionalModalidadeDisciplinaMista = modalidadeDisciplinaRepositorio.findById(3).get();

        for (int r = 0; r < rows; r++) {
            row = sheet.getRow(r);
            if (row != null && r > 0) {

                String codDisciplina = row.getCell(1).getStringCellValue();
//                System.out.println(codDisciplina);

                Optional<Disciplina> optionalDisciplina = disciplinaRepositorio.findByCodigo(codDisciplina);

                if (optionalDisciplina.isPresent()) {
                    Double periodo = row.getCell(0).getNumericCellValue();
//                System.out.println(periodo);
                    optionalDisciplina.get().setPeriodoMinimo(periodo.intValue());

                    Double cargaHoraria = row.getCell(2).getNumericCellValue();
//                System.out.println(cargaHoraria);
                    optionalDisciplina.get().setCargaHoraria(cargaHoraria.intValue());

                    String modalidade = row.getCell(3).getStringCellValue();
//                System.out.println(modalidade);

                    if (modalidade.equals("PRESENCIAL")) {
                        optionalDisciplina.get().setModalidadeDisciplina(optionalModalidadeDisciplinaPresencial);
                    } else if (modalidade.equals("ONLINE (EAD)")) {
                        optionalDisciplina.get().setModalidadeDisciplina(optionalModalidadeDisciplinaEad);
                    } else if (modalidade.equals("MISTA (PRES. + EAD)")) {
                        optionalDisciplina.get().setModalidadeDisciplina(optionalModalidadeDisciplinaMista);
                    }

                    try {
                        Double vagasAutorizadas = row.getCell(5).getNumericCellValue();
//                        System.out.println(vagasAutorizadas);
                        optionalDisciplina.get().setQuantidadeVagaAutorizada(vagasAutorizadas.intValue());
                    } catch (Exception e) {

                    }

                    disciplinaRepositorio.save(optionalDisciplina.get());

                    String classificacao = row.getCell(4).getStringCellValue();

                    Optional<ClassificacaoCurso> optionalClassificacaoCurso = classificacaoCursoRepositorio.findByNome(classificacao);

                    if (!optionalClassificacaoCurso.isPresent()) {
                        classificacaoCursoRepositorio.save(new ClassificacaoCurso(null, classificacao));
                        ClassificacaoCurso classificacaoCurso = classificacaoCursoRepositorio.findByNome(classificacao).get();
                        CursoDisciplinaPK cursoDisciplinaPK = new CursoDisciplinaPK(optionalCurso, optionalDisciplina.get());
                        CursoDisciplina cursoDisciplina = new CursoDisciplina(cursoDisciplinaPK, classificacaoCurso);
                        cursoDisciplinaRepositorio.save(cursoDisciplina);
                    } else {
                        CursoDisciplinaPK cursoDisciplinaPK = new CursoDisciplinaPK(optionalCurso, optionalDisciplina.get());
                        CursoDisciplina cursoDisciplina = new CursoDisciplina(cursoDisciplinaPK, optionalClassificacaoCurso.get());
                        cursoDisciplinaRepositorio.save(cursoDisciplina);
                    }

                }

            }
        }
    }

    private void inserirCursos() throws Exception {

        Optional<ModalidadeCurso> modalidadeCurso = modalidadeCursoRepositorio.findById(1);
        Optional<StatusCurso> statusCurso = statusCursoRepositorio.findById(1);
        Optional<GrauCurso> grauCurso = grauCursoRepositorio.findById(1);

        Optional<Professor> jonas = professorRepositorio.findByFuncionarioPessoaNome("JONAS HENRIQUE MENDONÇA");
        Optional<Professor> pedro = professorRepositorio.findByFuncionarioPessoaNome("PEDRO DE JESUS CERINO");
        Optional<Professor> daniela = professorRepositorio.findByFuncionarioPessoaNome("DANIELA TRINDADE DE SOUSA");
        Optional<Professor> kellen = professorRepositorio.findByFuncionarioPessoaNome("KELLEN DE SOUZA SINGH");

        Curso cursoSistemas116 = Curso.builder()
                .cargaHorariaEletivas(72)
                .cargaHorariaMinima(3000)
                .horasAtividadesComplementares(320)
                .nome("SISTEMAS DE INFORMAÇÃO")
                .modalidadeCurso(modalidadeCurso.get())
                .statusCurso(statusCurso.get())
                .grauCurso(grauCurso.get())
                .codigo("67601")
                .inicio(parseDate("26/01/2004"))
                .quantidadeVagaAutorizada(100)
                .enade(2)
                .cpc(3)
                .cc(4)
                .coordenador(jonas.get())
                .build();

        Curso cursoSistemas118 = Curso.builder()
                .cargaHorariaEletivas(72)
                .cargaHorariaMinima(3000)
                .horasAtividadesComplementares(540)
                .nome("SISTEMAS DE INFORMAÇÃO")
                .modalidadeCurso(modalidadeCurso.get())
                .statusCurso(statusCurso.get())
                .grauCurso(grauCurso.get())
                .codigo("67601")
                .inicio(parseDate("26/01/2004"))
                .quantidadeVagaAutorizada(100)
                .enade(2)
                .cpc(3)
                .cc(4)
                .coordenador(jonas.get())
                .build();

        Curso enfermagem118 = Curso.builder()
                .cargaHorariaEletivas(36)
                .cargaHorariaMinima(4000)
                .horasAtividadesComplementares(120)
                .nome("ENFERMAGEM")
                .modalidadeCurso(modalidadeCurso.get())
                .statusCurso(statusCurso.get())
                .grauCurso(grauCurso.get())
                .quantidadeVagaAutorizada(200)
                .codigo("1361383")
                .inicio(parseDate("29/08/2016"))
                .coordenador(daniela.get())
                .build();

        Curso direito118 = Curso.builder()
                .cargaHorariaEletivas(108)
                .cargaHorariaMinima(3700)
                .horasAtividadesComplementares(240)
                .nome("DIREITO")
                .modalidadeCurso(modalidadeCurso.get())
                .statusCurso(statusCurso.get())
                .grauCurso(grauCurso.get())
                .quantidadeVagaAutorizada(160)
                .codigo("79764")
                .inicio(parseDate("24/01/2005"))
                .enade(2)
                .cpc(3)
                .cc(4)
                .coordenador(pedro.get())
                .build();

        Curso engenhariaCivil118 = Curso.builder()
                .cargaHorariaEletivas(72)
                .cargaHorariaMinima(3600)
                .horasAtividadesComplementares(220)
                .nome("ENGENHARIA CIVIL")
                .modalidadeCurso(modalidadeCurso.get())
                .statusCurso(statusCurso.get())
                .grauCurso(grauCurso.get())
                .codigo("1185157")
                .inicio(parseDate("06/03/2014"))
                .quantidadeVagaAutorizada(150)
                .enade(2)
                .cc(4)
                .coordenador(kellen.get())
                .build();

        cursoRepositorio.saveAll(Arrays.asList(cursoSistemas116, cursoSistemas118, enfermagem118, direito118, engenhariaCivil118));

        Matriz matriz116 = new Matriz(null, 116, Arrays.asList(cursoSistemas116));
        Matriz matriz118 = new Matriz(null, 118, Arrays.asList(cursoSistemas118, enfermagem118, direito118, engenhariaCivil118));
        matrizRepositorio.saveAll(Arrays.asList(matriz116, matriz118));

        Optional<Instituicao> instituicao = instituicaoRepositorio.findById(1);
        instituicao.get().setCursos(Arrays.asList(cursoSistemas116, cursoSistemas118, enfermagem118, direito118, engenhariaCivil118));
        instituicaoRepositorio.save(instituicao.get());
    }

    private Date parseDate(String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }
}
