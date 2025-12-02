import { User, Heart, Users, LogOut } from 'lucide-react';

interface HomeIdosoProps {
  onNavigate: (screen: string) => void;
}

export function HomeIdoso({ onNavigate }: HomeIdosoProps) {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 p-4 py-8">
      <div className="max-w-4xl mx-auto">
        {/* Header */}
        <div className="flex items-center justify-between mb-8">
          <div className="flex items-center gap-4">
            <div className="bg-gradient-to-br from-blue-200 to-purple-200 p-3 rounded-full">
              <Heart className="w-8 h-8 text-blue-600" />
            </div>
            <div>
              <h1 className="text-gray-800">Olá, Maria!</h1>
              <p className="text-gray-500">Gerencie suas listas de desejos</p>
            </div>
          </div>
          <button 
            onClick={() => onNavigate('login')}
            className="p-3 hover:bg-white rounded-xl transition-colors"
          >
            <LogOut className="w-6 h-6 text-gray-600" />
          </button>
        </div>

        {/* Menu Cards */}
        <div className="grid md:grid-cols-3 gap-6">
          {/* Ver Perfil */}
          <button 
            onClick={() => onNavigate('perfil')}
            className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105"
          >
            <div className="bg-gradient-to-br from-blue-100 to-blue-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-blue-200 group-hover:to-blue-300 transition-all">
              <User className="w-10 h-10 text-blue-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Meu Perfil</h2>
            <p className="text-gray-500">Suas informações pessoais</p>
          </button>

          {/* Ver Lista de Desejos */}
          <button 
            onClick={() => onNavigate('opcoes-lista')}
            className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105"
          >
            <div className="bg-gradient-to-br from-purple-100 to-purple-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-purple-200 group-hover:to-purple-300 transition-all">
              <Heart className="w-10 h-10 text-purple-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Minha Lista</h2>
            <p className="text-gray-500">Crie e gerencie seus desejos</p>
          </button>

          {/* Ver Familiares */}
          <button 
            onClick={() => onNavigate('lista-usuarios')}
            className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105"
          >
            <div className="bg-gradient-to-br from-green-100 to-green-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-green-200 group-hover:to-green-300 transition-all">
              <Users className="w-10 h-10 text-green-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Meus Familiares</h2>
            <p className="text-gray-500">Familiares que acompanham você</p>
          </button>
        </div>

        {/* Info Banner */}
        <div className="mt-8 bg-gradient-to-r from-blue-100 to-purple-100 border-2 border-blue-200 rounded-3xl p-6">
          <div className="flex gap-3">
            <div className="text-blue-600 text-xl">✨</div>
            <div>
              <h3 className="text-blue-900 mb-2">Você tem controle total!</h3>
              <p className="text-blue-800 text-sm">Crie suas listas, adicione itens e seus familiares poderão visualizar e atualizar o status para ajudá-lo a realizar seus desejos.</p>
            </div>
          </div>
        </div>

        {/* Status da Lista */}
        <div className="mt-6 bg-white rounded-3xl shadow-lg p-6">
          <h3 className="text-gray-800 mb-4">Status da Sua Lista</h3>
          <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
            <div className="bg-yellow-50 p-4 rounded-xl text-center">
              <div className="text-yellow-600 mb-1">5</div>
              <div className="text-gray-600 text-sm">Pendentes</div>
            </div>
            <div className="bg-blue-50 p-4 rounded-xl text-center">
              <div className="text-blue-600 mb-1">2</div>
              <div className="text-gray-600 text-sm">Em Andamento</div>
            </div>
            <div className="bg-green-50 p-4 rounded-xl text-center">
              <div className="text-green-600 mb-1">8</div>
              <div className="text-gray-600 text-sm">Concluídos</div>
            </div>
            <div className="bg-gray-50 p-4 rounded-xl text-center">
              <div className="text-gray-600 mb-1">1</div>
              <div className="text-gray-600 text-sm">Cancelados</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
