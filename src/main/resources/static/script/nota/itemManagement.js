/**
 * função para iterar várias propriedades para uma nota
 */

function addItem() {
    const container = document.getElementById('itensContainer');
    const itemIndex = container.children.length;
    const itemTemplate = `
        <div class="item row mb-3">
            <div class="col-md-3">
                <div class="form-group">
                    <label for="propriedade">Propriedade</label>
                    <select id="propriedade-${itemIndex}" name="itens[${itemIndex}].propriedade.id" class="form-control" required>
                        <option value="" disabled selected>Selecione uma propriedade</option>
                        ${Array.from(document.querySelectorAll('#propriedade option')).map(option => `<option value="${option.value}">${option.text}</option>`).join('')}
                    </select>
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <label for="quantidade-${itemIndex}">Quantidade</label>
                    <input type="number" id="quantidade-${itemIndex}" name="itens[${itemIndex}].quantidade" class="form-control" required>
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <label for="unidadeMedida-${itemIndex}">Unidade de Medida</label>
                    <select id="unidadeMedida-${itemIndex}" name="itens[${itemIndex}].unidadeMedida" class="form-control" required>
                        <option value="" disabled selected>Selecione uma unidade de medida</option>
                        ${Array.from(document.querySelectorAll('#unidadeMedida option')).map(option => `<option value="${option.value}">${option.text}</option>`).join('')}
                    </select>
                </div>
            </div>
            <div class="col-md-1 d-flex align-items-end">
                <button type="button" class="btn btn-danger btn-sm" onclick="removeItem(this)">x</button>
            </div>
        </div>
    `;
    container.insertAdjacentHTML('beforeend', itemTemplate);
    updateRemoveButtons();
}

function removeItem(button) {
    const item = button.closest('.item');
    const container = document.getElementById('itensContainer');
    container.removeChild(item);
    if (container.children.length === 0) {
        addItem();
    }
    updateRemoveButtons();
}

function updateRemoveButtons() {
    const items = document.querySelectorAll('#itensContainer .item');
    items.forEach((item, index) => {
        const removeButton = item.querySelector('.btn-danger');
        if (index === 0 && items.length === 1) {
            removeButton.disabled = true;
        } else {
            removeButton.disabled = false;
        }
    });
}

document.addEventListener('DOMContentLoaded', () => {
    updateRemoveButtons();
});
