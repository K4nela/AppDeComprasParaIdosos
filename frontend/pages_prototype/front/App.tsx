import { useState } from 'react';
import { LoginScreen } from './components/LoginScreen';
import { SignupScreen } from './components/SignupScreen';
import { HomeFamiliar } from './components/HomeFamiliar';
import { HomeIdoso } from './components/HomeIdoso';
import { HomeAdmin } from './components/HomeAdmin';
import { ProfileScreen } from './components/ProfileScreen';
import { UpdateProfileScreen } from './components/UpdateProfileScreen';
import { UserListScreen } from './components/UserListScreen';
import { UserDetailScreen } from './components/UserDetailScreen';
import { ManageUsersScreen } from './components/ManageUsersScreen';
import { WishlistOptionsScreen } from './components/WishlistOptionsScreen';
import { CreateEditListScreen } from './components/CreateEditListScreen';
import { ViewWishlistScreen } from './components/ViewWishlistScreen';
import { ViewWishlistFamiliar } from './components/ViewWishlistFamiliar';
import { ViewWishlistDetailsScreen } from './components/ViewWishlistDetailsScreen';
import { ManageItemsScreen } from './components/ManageItemsScreen';
import { EditItemScreen } from './components/EditItemScreen';
import { StatusScreen } from './components/StatusScreen';
import { PermissionsGuideScreen } from './components/PermissionsGuideScreen';
import { Menu, X } from 'lucide-react';

type Screen = 
  | 'login' 
  | 'cadastro' 
  | 'home-familiar' 
  | 'home-idoso' 
  | 'home-admin'
  | 'perfil'
  | 'atualizar-dados'
  | 'lista-usuarios'
  | 'detalhe-usuario'
  | 'gerenciar-usuarios'
  | 'opcoes-lista'
  | 'criar-lista'
  | 'visualizar-lista'
  | 'visualizar-lista-familiar'
  | 'visualizar-lista-detalhes'
  | 'itens-lista'
  | 'editar-item'
  | 'status-item'
  | 'guia-permissoes';

export default function App() {
  const [currentScreen, setCurrentScreen] = useState<Screen>('login');
  const [menuOpen, setMenuOpen] = useState(false);

  const navigate = (screen: Screen) => {
    setCurrentScreen(screen);
    setMenuOpen(false);
  };

  const renderScreen = () => {
    switch (currentScreen) {
      case 'login':
        return <LoginScreen onNavigate={navigate} />;
      case 'cadastro':
        return <SignupScreen onNavigate={navigate} />;
      case 'home-familiar':
        return <HomeFamiliar onNavigate={navigate} />;
      case 'home-idoso':
        return <HomeIdoso onNavigate={navigate} />;
      case 'home-admin':
        return <HomeAdmin onNavigate={navigate} />;
      case 'perfil':
        return <ProfileScreen onNavigate={navigate} />;
      case 'atualizar-dados':
        return <UpdateProfileScreen onNavigate={navigate} />;
      case 'lista-usuarios':
        return <UserListScreen onNavigate={navigate} />;
      case 'detalhe-usuario':
        return <UserDetailScreen onNavigate={navigate} />;
      case 'gerenciar-usuarios':
        return <ManageUsersScreen onNavigate={navigate} />;
      case 'opcoes-lista':
        return <WishlistOptionsScreen onNavigate={navigate} />;
      case 'criar-lista':
        return <CreateEditListScreen onNavigate={navigate} />;
      case 'visualizar-lista':
        return <ViewWishlistScreen onNavigate={navigate} />;
      case 'visualizar-lista-familiar':
        return <ViewWishlistFamiliar onNavigate={navigate} />;
      case 'visualizar-lista-detalhes':
        return <ViewWishlistDetailsScreen onNavigate={navigate} />;
      case 'itens-lista':
        return <ManageItemsScreen onNavigate={navigate} />;
      case 'editar-item':
        return <EditItemScreen onNavigate={navigate} />;
      case 'status-item':
        return <StatusScreen onNavigate={navigate} />;
      case 'guia-permissoes':
        return <PermissionsGuideScreen onNavigate={navigate} />;
      default:
        return <LoginScreen onNavigate={navigate} />;
    }
  };

  return (
    <div className="relative">
      {/* Screen Navigator Menu (only show when not on login/signup) */}
      {currentScreen !== 'login' && currentScreen !== 'cadastro' && (
        <>
          <button
            onClick={() => setMenuOpen(!menuOpen)}
            className="fixed top-4 right-4 z-50 bg-white p-3 rounded-xl shadow-lg hover:shadow-xl transition-all"
          >
            {menuOpen ? <X className="w-6 h-6 text-gray-600" /> : <Menu className="w-6 h-6 text-gray-600" />}
          </button>

          {menuOpen && (
            <div className="fixed inset-0 bg-black bg-opacity-50 z-40" onClick={() => setMenuOpen(false)}>
              <div className="fixed right-0 top-0 bottom-0 w-80 bg-white shadow-2xl p-6 overflow-y-auto" onClick={(e) => e.stopPropagation()}>
                <h2 className="text-gray-800 mb-6 mt-12">Navegação de Telas</h2>
                
                <div className="space-y-6">
                  {/* Auth Screens */}
                  <div>
                    <h3 className="text-gray-600 mb-3 text-sm">Autenticação</h3>
                    <div className="space-y-2">
                      <button onClick={() => navigate('login')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Login
                      </button>
                      <button onClick={() => navigate('cadastro')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Cadastro
                      </button>
                      <button onClick={() => navigate('guia-permissoes')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Guia de Permissões
                      </button>
                    </div>
                  </div>

                  {/* Home Screens */}
                  <div>
                    <h3 className="text-gray-600 mb-3 text-sm">Páginas Iniciais</h3>
                    <div className="space-y-2">
                      <button onClick={() => navigate('home-familiar')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Home - Familiar
                      </button>
                      <button onClick={() => navigate('home-idoso')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Home - Idoso
                      </button>
                      <button onClick={() => navigate('home-admin')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Home - Admin
                      </button>
                    </div>
                  </div>

                  {/* Profile Screens */}
                  <div>
                    <h3 className="text-gray-600 mb-3 text-sm">Perfil</h3>
                    <div className="space-y-2">
                      <button onClick={() => navigate('perfil')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Ver Perfil
                      </button>
                      <button onClick={() => navigate('atualizar-dados')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Atualizar Dados
                      </button>
                    </div>
                  </div>

                  {/* Admin Screens */}
                  <div>
                    <h3 className="text-gray-600 mb-3 text-sm">Administração</h3>
                    <div className="space-y-2">
                      <button onClick={() => navigate('gerenciar-usuarios')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Gerenciar Usuários
                      </button>
                      <button onClick={() => navigate('lista-usuarios')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Lista de Usuários
                      </button>
                      <button onClick={() => navigate('detalhe-usuario')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Detalhe do Usuário
                      </button>
                    </div>
                  </div>

                  {/* Wishlist Screens - Elder */}
                  <div>
                    <h3 className="text-gray-600 mb-3 text-sm">Lista de Desejos (Idoso)</h3>
                    <div className="space-y-2">
                      <button onClick={() => navigate('opcoes-lista')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Opções da Lista
                      </button>
                      <button onClick={() => navigate('criar-lista')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Criar/Editar Lista
                      </button>
                      <button onClick={() => navigate('visualizar-lista')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Visualizar Lista
                      </button>
                    </div>
                  </div>

                  {/* Wishlist Screens - Family */}
                  <div>
                    <h3 className="text-gray-600 mb-3 text-sm">Listas (Familiar)</h3>
                    <div className="space-y-2">
                      <button onClick={() => navigate('visualizar-lista-familiar')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Listas dos Idosos
                      </button>
                      <button onClick={() => navigate('visualizar-lista-detalhes')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Detalhes da Lista
                      </button>
                    </div>
                  </div>

                  {/* Items Screens */}
                  <div>
                    <h3 className="text-gray-600 mb-3 text-sm">Itens</h3>
                    <div className="space-y-2">
                      <button onClick={() => navigate('itens-lista')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Gerenciar Itens
                      </button>
                      <button onClick={() => navigate('editar-item')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Editar Item
                      </button>
                      <button onClick={() => navigate('status-item')} className="w-full text-left px-4 py-2 bg-gray-50 hover:bg-purple-50 rounded-lg transition-colors">
                        Status do Item
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          )}
        </>
      )}

      {/* Main Content */}
      {renderScreen()}
    </div>
  );
}
