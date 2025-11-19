import { User, Heart, Users, LogOut } from 'lucide-react';

interface HomeFamiliarProps {
  onNavigate: (screen: string) => void;
}

export function HomeFamiliar({ onNavigate }: HomeFamiliarProps) {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 p-4 py-8">
      <div className="max-w-4xl mx-auto">
        {/* Header */}
        <div className="flex items-center justify-between mb-8">
          <div className="flex items-center gap-4">
            <div className="bg-gradient-to-br from-purple-200 to-blue-200 p-3 rounded-full">
              <Heart className="w-8 h-8 text-purple-600" />
            </div>
            <div>
              <h1 className="text-gray-800">Olá, Familiar!</h1>
              <p className="text-gray-500">Bem-vindo à sua área</p>
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
            <h2 className="text-gray-800 mb-2">Ver Perfil</h2>
            <p className="text-gray-500">Visualize e edite suas informações pessoais</p>
          </button>

          {/* Ver Lista de Desejos */}
          <button 
            onClick={() => onNavigate('visualizar-lista-familiar')}
            className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105"
          >
            <div className="bg-gradient-to-br from-purple-100 to-purple-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-purple-200 group-hover:to-purple-300 transition-all">
              <Heart className="w-10 h-10 text-purple-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Listas dos Idosos</h2>
            <p className="text-gray-500">Visualize e atualize status dos desejos</p>
          </button>

          {/* Ver Idosos */}
          <button 
            onClick={() => onNavigate('lista-usuarios')}
            className="bg-white rounded-3xl shadow-lg p-8 hover:shadow-xl transition-all text-left group hover:scale-105"
          >
            <div className="bg-gradient-to-br from-green-100 to-green-200 p-4 rounded-2xl inline-flex mb-4 group-hover:from-green-200 group-hover:to-green-300 transition-all">
              <Users className="w-10 h-10 text-green-600" />
            </div>
            <h2 className="text-gray-800 mb-2">Ver Idosos</h2>
            <p className="text-gray-500">Visualize os idosos conectados a você</p>
          </button>
        </div>

        {/* Info Banner */}
        <div className="mt-8 bg-gradient-to-r from-purple-100 to-blue-100 border-2 border-purple-200 rounded-3xl p-6">
          <div className="flex gap-3">
            <div className="text-purple-600 text-xl">💡</div>
            <div>
              <h3 className="text-purple-900 mb-2">Suas Permissões como Familiar</h3>
              <p className="text-purple-800 text-sm mb-3">Você pode visualizar todas as listas dos idosos e atualizar o status dos itens para ajudar a realizar os desejos deles.</p>
              <div className="flex flex-wrap gap-2">
                <span className="px-3 py-1 bg-white text-purple-700 rounded-full text-sm">✓ Visualizar listas</span>
                <span className="px-3 py-1 bg-white text-purple-700 rounded-full text-sm">✓ Atualizar status</span>
                <span className="px-3 py-1 bg-white text-purple-700 rounded-full text-sm">✗ Editar itens</span>
              </div>
            </div>
          </div>
        </div>

        {/* Quick Info */}
        <div className="mt-6 bg-white rounded-3xl shadow-lg p-6">
          <h3 className="text-gray-800 mb-4">Notificações Recentes</h3>
          <div className="space-y-3">
            <div className="flex items-center gap-3 p-4 bg-purple-50 rounded-xl">
              <div className="w-2 h-2 bg-purple-400 rounded-full"></div>
              <p className="text-gray-700">Novo item adicionado na lista de Maria Silva</p>
            </div>
            <div className="flex items-center gap-3 p-4 bg-blue-50 rounded-xl">
              <div className="w-2 h-2 bg-blue-400 rounded-full"></div>
              <p className="text-gray-700">João Santos atualizou seu perfil</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
