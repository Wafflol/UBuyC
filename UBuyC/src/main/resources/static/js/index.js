
const selectedTags = new Set(); // To track selected tags

const tagsContainer = document.querySelector('.tags');
const tagCount = document.getElementById('tag-count');
const removeAllButton = document.getElementById('remove-all');

// Handle tag selection
tagsContainer.addEventListener('click', (e) => {
    if (e.target.classList.contains('tag')) {
        const tag = e.target.dataset.tag;

        if (selectedTags.has(tag)) {
            selectedTags.delete(tag);
            e.target.classList.remove('selected');
        } else {
            selectedTags.add(tag);
            e.target.classList.add('selected');
        }

    }
});

// Remove all selected tags
removeAllButton.addEventListener('click', () => {
    selectedTags.clear();
    document.querySelectorAll('.tag').forEach((tag) => tag.classList.remove('selected'));
    updateTagCount();
});

// Open the modal
function openModal() {
    document.getElementById('createModal').style.display = 'flex';
}

// Close the modal
function closeModal() {
    document.getElementById('createModal').style.display = 'none';
    selectedTags.clear();
    document.querySelectorAll('.tag').forEach((tag) => tag.classList.remove('selected'));
    updateTagCount();
}


// Tag selection
document.querySelectorAll('.tag').forEach(tag => {
    tag.addEventListener('click', function () {
        tag.classList.toggle('selected');
    });
});



