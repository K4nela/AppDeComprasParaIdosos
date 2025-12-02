import { ArrowLeft, Clock, PlayCircle, CheckCircle, XCircle } from 'lucide-react';

interface StatusScreenProps {
  onNavigate: (screen: string) => void;
}

export function StatusScreen({ onNavigate }: StatusScreenProps) {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 p-4 py-8">
      <div className="max-w-4xl mx-auto">
        {/* Header */}
        <div className="flex items-center gap-4 mb-6">
          <button 
            onClick={() => onNavigate('visualizar-lista')}
            className="p-2 hover:bg-white rounded-xl transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-gray-600" />
          </button>
          <h1 className="text-gray-800">Atualizar Status do Item</h1>
        </div>

        {/* Current Item Info */}
        <div className="bg-white rounded-3xl shadow-lg p-6 mb-6">
          <h3 className="text-gray-600 mb-2">Item Selecionado</h3>
          <h2 className="text-gray-800">Cadeira de Balanço</h2>
          <p className="text-gray-500 mt-1">Cadeira confortável para relaxar na varanda</p>
          <div className="mt-3 flex items-center gap-2">
            <span className="text-gray-500 text-sm">Status atual:</span>
            <span className="px-3 py-1 bg-blue-100 text-blue-700 border-2 border-blue-200 rounded-full text-sm">Em andamento</span>
          </div>
        </div>

        {/* Permission Info */}
        <div className="bg-green-100 border-2 border-green-200 rounded-2xl p-4 mb-6">
          <div className="flex gap-3">
            <div className="text-green-600">✅</div>
            <div>
              <div className="text-green-800 mb-1">Atualização de Status</div>
              <p className="text-green-700 text-sm">Tanto idosos quanto familiares podem atualizar o status dos itens. Escolha o novo status abaixo:</p>
            </div>
          </div>
        </div>

        {/* Status Options */}
        <div className="grid md:grid-cols-2 gap-6">
          {/* Pendente */}
          <button className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105 border-4 border-transparent hover:border-yellow-200">
            <div className="bg-gradient-to-br from-yellow-100 to-yellow-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-yellow-200 group-hover:to-yellow-300 transition-all">
              <Clock className="w-10 h-10 text-yellow-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Pendente</h2>
            <p className="text-gray-500">O item ainda não teve nenhuma ação iniciada</p>
            <div className="mt-4 pt-4 border-t border-gray-100">
              <div className="text-yellow-600 text-sm">Status inicial padrão</div>
            </div>
          </button>

          {/* Em Andamento */}
          <button className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105 border-4 border-transparent hover:border-blue-200">
            <div className="bg-gradient-to-br from-blue-100 to-blue-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-blue-200 group-hover:to-blue-300 transition-all">
              <PlayCircle className="w-10 h-10 text-blue-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Em Andamento</h2>
            <p className="text-gray-500">Alguém está trabalhando para realizar este desejo</p>
            <div className="mt-4 pt-4 border-t border-gray-100">
              <div className="text-blue-600 text-sm">Indica que o presente está sendo providenciado</div>
            </div>
          </button>

          {/* Concluído */}
          <button className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105 border-4 border-transparent hover:border-green-200">
            <div className="bg-gradient-to-br from-green-100 to-green-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-green-200 group-hover:to-green-300 transition-all">
              <CheckCircle className="w-10 h-10 text-green-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Concluído</h2>
            <p className="text-gray-500">O desejo foi realizado com sucesso!</p>
            <div className="mt-4 pt-4 border-t border-gray-100">
              <div className="text-green-600 text-sm">O item foi adquirido e entregue</div>
            </div>
          </button>

          {/* Cancelado */}
          <button className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105 border-4 border-transparent hover:border-gray-200">
            <div className="bg-gradient-to-br from-gray-100 to-gray-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-gray-200 group-hover:to-gray-300 transition-all">
              <XCircle className="w-10 h-10 text-gray-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Cancelado</h2>
            <p className="text-gray-500">Este desejo não será mais realizado</p>
            <div className="mt-4 pt-4 border-t border-gray-100">
              <div className="text-gray-600 text-sm">Item removido ou não é mais necessário</div>
            </div>
          </button>
        </div>

        {/* Help Section */}
        <div className="mt-8 bg-white rounded-3xl shadow-lg p-6">
          <h3 className="text-gray-800 mb-4">Como funciona o status?</h3>
          <div className="space-y-3">
            <div className="flex gap-3 p-4 bg-yellow-50 rounded-xl">
              <Clock className="w-5 h-5 text-yellow-600 mt-0.5" />
              <div>
                <div className="text-gray-800 mb-1">Pendente</div>
                <p className="text-gray-600 text-sm">Quando um item é criado, ele começa como "Pendente". Indica que ninguém ainda começou a trabalhar nele.</p>
              </div>
            </div>
            <div className="flex gap-3 p-4 bg-blue-50 rounded-xl">
              <PlayCircle className="w-5 h-5 text-blue-600 mt-0.5" />
              <div>
                <div className="text-gray-800 mb-1">Em Andamento</div>
                <p className="text-gray-600 text-sm">Quando um familiar decide realizar o desejo, ele pode marcar como "Em Andamento" para avisar aos outros.</p>
              </div>
            </div>
            <div className="flex gap-3 p-4 bg-green-50 rounded-xl">
              <CheckCircle className="w-5 h-5 text-green-600 mt-0.5" />
              <div>
                <div className="text-gray-800 mb-1">Concluído</div>
                <p className="text-gray-600 text-sm">Quando o presente é comprado e entregue, marca-se como "Concluído". O desejo foi realizado!</p>
              </div>
            </div>
            <div className="flex gap-3 p-4 bg-gray-50 rounded-xl">
              <XCircle className="w-5 h-5 text-gray-600 mt-0.5" />
              <div>
                <div className="text-gray-800 mb-1">Cancelado</div>
                <p className="text-gray-600 text-sm">Se o item não é mais necessário ou foi removido da lista, pode ser marcado como "Cancelado".</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
