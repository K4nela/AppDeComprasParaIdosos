import { ArrowLeft, User, Users, Shield, Check, X } from 'lucide-react';

interface PermissionsGuideScreenProps {
  onNavigate: (screen: string) => void;
}

export function PermissionsGuideScreen({ onNavigate }: PermissionsGuideScreenProps) {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 p-4 py-8">
      <div className="max-w-4xl mx-auto">
        {/* Header */}
        <div className="flex items-center gap-4 mb-6">
          <button 
            onClick={() => onNavigate('login')}
            className="p-2 hover:bg-white rounded-xl transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-gray-600" />
          </button>
          <h1 className="text-gray-800">Guia de Permissões</h1>
        </div>

        {/* Intro */}
        <div className="bg-white rounded-3xl shadow-lg p-8 mb-6">
          <h2 className="text-gray-800 mb-4">Como funciona o easypeasy?</h2>
          <p className="text-gray-600 mb-4">
            O easypeasy é um sistema que conecta idosos e seus familiares através de listas de desejos. 
            Cada tipo de usuário tem permissões específicas para garantir uma experiência organizada e respeitosa.
          </p>
          <p className="text-gray-600">
            Veja abaixo o que cada tipo de usuário pode fazer:
          </p>
        </div>

        {/* User Types */}
        <div className="space-y-6">
          {/* Elder Permissions */}
          <div className="bg-white rounded-3xl shadow-lg p-8">
            <div className="flex items-center gap-4 mb-6">
              <div className="bg-gradient-to-br from-blue-100 to-blue-200 p-4 rounded-2xl">
                <User className="w-8 h-8 text-blue-600" />
              </div>
              <div>
                <h2 className="text-gray-800">Idoso</h2>
                <p className="text-gray-500">Criador e gerenciador das listas</p>
              </div>
            </div>

            <div className="space-y-3">
              <div className="flex items-start gap-3 p-3 bg-green-50 rounded-xl">
                <Check className="w-5 h-5 text-green-600 mt-0.5" />
                <div className="flex-1">
                  <div className="text-gray-800">Criar listas de desejos</div>
                  <p className="text-gray-500 text-sm">Pode criar quantas listas desejar</p>
                </div>
              </div>

              <div className="flex items-start gap-3 p-3 bg-green-50 rounded-xl">
                <Check className="w-5 h-5 text-green-600 mt-0.5" />
                <div className="flex-1">
                  <div className="text-gray-800">Editar listas</div>
                  <p className="text-gray-500 text-sm">Modificar nome e descrição das suas listas</p>
                </div>
              </div>

              <div className="flex items-start gap-3 p-3 bg-green-50 rounded-xl">
                <Check className="w-5 h-5 text-green-600 mt-0.5" />
                <div className="flex-1">
                  <div className="text-gray-800">Adicionar itens</div>
                  <p className="text-gray-500 text-sm">Criar novos itens de desejo com detalhes completos</p>
                </div>
              </div>

              <div className="flex items-start gap-3 p-3 bg-green-50 rounded-xl">
                <Check className="w-5 h-5 text-green-600 mt-0.5" />
                <div className="flex-1">
                  <div className="text-gray-800">Editar itens</div>
                  <p className="text-gray-500 text-sm">Atualizar informações dos itens existentes</p>
                </div>
              </div>

              <div className="flex items-start gap-3 p-3 bg-green-50 rounded-xl">
                <Check className="w-5 h-5 text-green-600 mt-0.5" />
                <div className="flex-1">
                  <div className="text-gray-800">Excluir itens e listas</div>
                  <p className="text-gray-500 text-sm">Remover itens ou listas completas</p>
                </div>
              </div>

              <div className="flex items-start gap-3 p-3 bg-green-50 rounded-xl">
                <Check className="w-5 h-5 text-green-600 mt-0.5" />
                <div className="flex-1">
                  <div className="text-gray-800">Atualizar status</div>
                  <p className="text-gray-500 text-sm">Alterar o status dos itens</p>
                </div>
              </div>

              <div className="flex items-start gap-3 p-3 bg-green-50 rounded-xl">
                <Check className="w-5 h-5 text-green-600 mt-0.5" />
                <div className="flex-1">
                  <div className="text-gray-800">Visualizar histórico</div>
                  <p className="text-gray-500 text-sm">Ver todo o histórico de alterações</p>
                </div>
              </div>
            </div>
          </div>

          {/* Family Permissions */}
          <div className="bg-white rounded-3xl shadow-lg p-8">
            <div className="flex items-center gap-4 mb-6">
              <div className="bg-gradient-to-br from-purple-100 to-purple-200 p-4 rounded-2xl">
                <Users className="w-8 h-8 text-purple-600" />
              </div>
              <div>
                <h2 className="text-gray-800">Familiar</h2>
                <p className="text-gray-500">Acompanha e ajuda a realizar desejos</p>
              </div>
            </div>

            <div className="space-y-3">
              <div className="flex items-start gap-3 p-3 bg-green-50 rounded-xl">
                <Check className="w-5 h-5 text-green-600 mt-0.5" />
                <div className="flex-1">
                  <div className="text-gray-800">Visualizar listas dos idosos</div>
                  <p className="text-gray-500 text-sm">Ver todas as listas criadas pelos idosos</p>
                </div>
              </div>

              <div className="flex items-start gap-3 p-3 bg-green-50 rounded-xl">
                <Check className="w-5 h-5 text-green-600 mt-0.5" />
                <div className="flex-1">
                  <div className="text-gray-800">Visualizar itens</div>
                  <p className="text-gray-500 text-sm">Ver todos os detalhes dos itens de desejo</p>
                </div>
              </div>

              <div className="flex items-start gap-3 p-3 bg-green-50 rounded-xl">
                <Check className="w-5 h-5 text-green-600 mt-0.5" />
                <div className="flex-1">
                  <div className="text-gray-800">Atualizar status dos itens</div>
                  <p className="text-gray-500 text-sm">Marcar itens como pendente, em andamento, concluído ou cancelado</p>
                </div>
              </div>

              <div className="flex items-start gap-3 p-3 bg-green-50 rounded-xl">
                <Check className="w-5 h-5 text-green-600 mt-0.5" />
                <div className="flex-1">
                  <div className="text-gray-800">Visualizar histórico</div>
                  <p className="text-gray-500 text-sm">Acompanhar o histórico de status</p>
                </div>
              </div>

              <div className="flex items-start gap-3 p-3 bg-red-50 rounded-xl">
                <X className="w-5 h-5 text-red-600 mt-0.5" />
                <div className="flex-1">
                  <div className="text-gray-800">Criar ou editar listas</div>
                  <p className="text-gray-500 text-sm">Apenas o idoso pode criar e gerenciar listas</p>
                </div>
              </div>

              <div className="flex items-start gap-3 p-3 bg-red-50 rounded-xl">
                <X className="w-5 h-5 text-red-600 mt-0.5" />
                <div className="flex-1">
                  <div className="text-gray-800">Adicionar, editar ou excluir itens</div>
                  <p className="text-gray-500 text-sm">Apenas o idoso pode gerenciar os itens</p>
                </div>
              </div>
            </div>
          </div>

          {/* Admin Permissions */}
          <div className="bg-white rounded-3xl shadow-lg p-8">
            <div className="flex items-center gap-4 mb-6">
              <div className="bg-gradient-to-br from-green-100 to-green-200 p-4 rounded-2xl">
                <Shield className="w-8 h-8 text-green-600" />
              </div>
              <div>
                <h2 className="text-gray-800">Administrador</h2>
                <p className="text-gray-500">Gerencia o sistema</p>
              </div>
            </div>

            <div className="space-y-3">
              <div className="flex items-start gap-3 p-3 bg-green-50 rounded-xl">
                <Check className="w-5 h-5 text-green-600 mt-0.5" />
                <div className="flex-1">
                  <div className="text-gray-800">Listar usuários</div>
                  <p className="text-gray-500 text-sm">Visualizar todos os usuários cadastrados</p>
                </div>
              </div>

              <div className="flex items-start gap-3 p-3 bg-green-50 rounded-xl">
                <Check className="w-5 h-5 text-green-600 mt-0.5" />
                <div className="flex-1">
                  <div className="text-gray-800">Editar usuários</div>
                  <p className="text-gray-500 text-sm">Atualizar informações de qualquer usuário</p>
                </div>
              </div>

              <div className="flex items-start gap-3 p-3 bg-green-50 rounded-xl">
                <Check className="w-5 h-5 text-green-600 mt-0.5" />
                <div className="flex-1">
                  <div className="text-gray-800">Excluir usuários</div>
                  <p className="text-gray-500 text-sm">Remover usuários do sistema</p>
                </div>
              </div>

              <div className="flex items-start gap-3 p-3 bg-green-50 rounded-xl">
                <Check className="w-5 h-5 text-green-600 mt-0.5" />
                <div className="flex-1">
                  <div className="text-gray-800">Visualizar estatísticas</div>
                  <p className="text-gray-500 text-sm">Acompanhar métricas do sistema</p>
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* Summary */}
        <div className="mt-6 bg-gradient-to-r from-purple-50 to-blue-50 rounded-3xl p-6">
          <h3 className="text-gray-800 mb-3">Resumo</h3>
          <div className="space-y-2 text-gray-700">
            <p className="flex items-start gap-2">
              <span className="text-blue-600">•</span>
              <span><strong>Idosos</strong> têm controle total sobre suas listas e itens</span>
            </p>
            <p className="flex items-start gap-2">
              <span className="text-purple-600">•</span>
              <span><strong>Familiares</strong> podem visualizar tudo e atualizar status para ajudar a realizar os desejos</span>
            </p>
            <p className="flex items-start gap-2">
              <span className="text-green-600">•</span>
              <span><strong>Administradores</strong> gerenciam usuários e o sistema</span>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}
