// Function to add or update an exam
const saveExam = async () => {
    const id = document.getElementById('idInput').value;
    const exam = document.getElementById('examInput').value;
    const startDate = document.getElementById('startDateInput').value;
    const endDate = document.getElementById('endDateInput').value;
    const retroDate = document.getElementById('retroDateInput').value;

    const newExam = { id, exam, startDate, endDate, retroDate };
    const method = id ? 'PUT' : 'POST';
    const url = id ? `/sort/exam/${id}` : '/sort/save';

    try {
        await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newExam),
        });

        // Clear the form
        document.getElementById('idInput').value = '';
        document.getElementById('examInput').value = '';
        document.getElementById('startDateInput').value = '';
        document.getElementById('endDateInput').value = '';
        document.getElementById('retroDateInput').value = '';

        await populateExams();
        await populateSortedExams();
    } catch (error) {
        console.error('Error saving exam:', error);
    }
};

// Function to fetch all exams
const fetchExams = async () => {
    try {
        const response = await fetch('/sort/getAll');
        if (!response.ok) throw new Error('Failed to fetch exams');
        const exams = await response.json();
        return exams;
    } catch (error) {
        console.error('Error fetching exams:', error);
        return [];
    }
};

// Function to fetch all sorted exams
const fetchSortedExams = async () => {
    try {
        const response = await fetch('/sort/sorted');
        if (!response.ok) throw new Error('Failed to fetch sorted exams');
        const sortedExams = await response.json();
        console.log('Sorted Exams:', sortedExams); // Debugging line
        return sortedExams;
    } catch (error) {
        console.error('Error fetching sorted exams:', error);
        return [];
    }
};

// Function to create exam table row
const createExamRow = (exam) => {
    const tr = document.createElement('tr');
    tr.dataset.index = exam.id;

    const examCell = document.createElement('td');
    examCell.textContent = exam.exam;

    const startDateCell = document.createElement('td');
    startDateCell.textContent = exam.startDate;

    const endDateCell = document.createElement('td');
    endDateCell.textContent = exam.endDate;

    const retroDateCell = document.createElement('td');
    retroDateCell.textContent = exam.retroDate;

    const actionsCell = document.createElement('td');

    const buttonEdit = document.createElement('button');
    buttonEdit.textContent = 'Edit';
    buttonEdit.classList.add('edit-exam');

    const buttonDelete = document.createElement('button');
    buttonDelete.textContent = 'Delete';
    buttonDelete.classList.add('delete-exam');

    actionsCell.appendChild(buttonEdit);
    actionsCell.appendChild(buttonDelete);

    tr.appendChild(examCell);
    tr.appendChild(startDateCell);
    tr.appendChild(endDateCell);
    tr.appendChild(retroDateCell);
    tr.appendChild(actionsCell);

    return tr;
};

// Function to populate exams
const populateExams = async () => {
    const examList = document.querySelector('#exam-list-items tbody');
    examList.innerHTML = '';
    const exams = await fetchExams();
    exams.forEach((exam) => {
        examList.appendChild(createExamRow(exam));
    });
};

// Function to populate sorted exams
const populateSortedExams = async () => {
    const sortedExamList = document.querySelector('#sorted-exam-list-items tbody');
    sortedExamList.innerHTML = '';
    const sortedExams = await fetchSortedExams();
    console.log('Populating Sorted Exams:', sortedExams); // Debugging line
    if (Array.isArray(sortedExams)) {
        sortedExams.forEach((exam) => {
            sortedExamList.appendChild(createExamRow(exam));
        });
    } else {
        console.error('Expected an array for sorted exams, received:', sortedExams);
    }
};

// Event listener for saving an exam
document.querySelector('.saveExam').addEventListener('click', saveExam);

// Event listener for editing an exam
document.addEventListener('click', async (e) => {
    if (e.target && e.target.classList.contains('edit-exam')) {
        const tr = e.target.parentElement.parentElement;
        const examId = tr.dataset.index;
        const response = await fetch(`/sort/exam/${examId}`);
        const exam = await response.json();

        document.getElementById('idInput').value = exam.id;
        document.getElementById('examInput').value = exam.exam;
        document.getElementById('startDateInput').value = exam.startDate;
        document.getElementById('endDateInput').value = exam.endDate;
        document.getElementById('retroDateInput').value = exam.retroDate;
    }
});

// Event listener for deleting an exam
document.addEventListener('click', async (e) => {
    if (e.target && e.target.classList.contains('delete-exam')) {
        const examId = e.target.parentElement.parentElement.dataset.index;

        await fetch(`/sort/${examId}`, {
            method: 'DELETE',
        });

        await populateExams();
        await populateSortedExams();
    }
});

// Initial population of exams and sorted exams
populateExams();
populateSortedExams();
