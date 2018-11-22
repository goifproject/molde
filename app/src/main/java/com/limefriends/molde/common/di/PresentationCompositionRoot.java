package com.limefriends.molde.common.di;

import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import com.limefriends.molde.common.helper.BitmapHelper;
import com.limefriends.molde.common.helper.SecretRetriever;
import com.limefriends.molde.model.database.dao.SearchHistoryDao;
import com.limefriends.molde.model.database.db.MoldeDatabase;
import com.limefriends.molde.model.common.FromSchemaToEntity;
import com.limefriends.molde.model.repository.usecase.SearchLocationUseCase;
import com.limefriends.molde.networking.common.NetworkHelper;
import com.limefriends.molde.screen.common.bottomNavigationViewHelper.BottomNavigationViewHelper;
import com.limefriends.molde.model.repository.Repository;
import com.limefriends.molde.model.repository.usecase.CardNewsUseCase;
import com.limefriends.molde.model.repository.usecase.CommentUseCase;
import com.limefriends.molde.model.repository.usecase.FaqUseCase;
import com.limefriends.molde.model.repository.usecase.FavoriteUseCase;
import com.limefriends.molde.model.repository.usecase.FeedResultUseCase;
import com.limefriends.molde.model.repository.usecase.FeedUseCase;
import com.limefriends.molde.model.repository.usecase.SafehouseUseCase;
import com.limefriends.molde.model.repository.usecase.ScrapUseCase;
import com.limefriends.molde.networking.service.MoldeRestfulService;
import com.limefriends.molde.screen.common.dialog.DialogFactory;
import com.limefriends.molde.screen.common.dialog.DialogManager;
import com.limefriends.molde.screen.common.fragmentFrameHelper.FragmentFrameHelper;
import com.limefriends.molde.screen.common.fragmentFrameHelper.FragmentFrameWrapper;
import com.limefriends.molde.screen.common.imageLoader.ImageLoader;
import com.limefriends.molde.screen.common.screensNavigator.ActivityScreenNavigator;
import com.limefriends.molde.screen.common.screensNavigator.FragmentScreenNavigator;
import com.limefriends.molde.screen.common.toastHelper.ToastHelper;
import com.limefriends.molde.screen.common.view.ViewFactory;
import com.limefriends.molde.screen.common.viewController.BackPressDispatcher;

import static com.limefriends.molde.model.database.db.MoldeDatabase.MOLDE_DB;

public class PresentationCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private final FragmentActivity mActivity;

    public PresentationCompositionRoot(CompositionRoot mCompositionRoot,
                                       FragmentActivity mActivity) {
        this.mCompositionRoot = mCompositionRoot;
        this.mActivity = mActivity;
    }

    private FragmentActivity getActivity() {
        return mActivity;
    }

    private FragmentManager getFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    private LayoutInflater getLayoutInflater() {
        return getActivity().getLayoutInflater();
    }


    /**
     * RestfulService
     */

    private MoldeRestfulService.Comment getCommentRestfulService() {
        return mCompositionRoot.getCommentRestfulService();
    }

    private MoldeRestfulService.Faq getFaqRestfulService() {
        return mCompositionRoot.getFaqRestfulService();
    }

    private MoldeRestfulService.Favorite getFavoriteRestfulService() {
        return mCompositionRoot.getFavoriteRestfulService();
    }

    private MoldeRestfulService.Feed getFeedRestfulService() {
        return mCompositionRoot.getFeedRestfulService();
    }

    private MoldeRestfulService.FeedResult getFeedResultRestfulService() {
        return mCompositionRoot.getFeedResultRestfulService();
    }

    private MoldeRestfulService.CardNews getCardNewsRestfulService() {
        return mCompositionRoot.getCardNewsRestfulService();
    }

    private MoldeRestfulService.Safehouse getSafehouseRestfulService() {
        return mCompositionRoot.getSafehouseRestfulService();
    }

    private MoldeRestfulService.Scrap getScrapRestfulService() {
        return mCompositionRoot.getScrapRestfulService();
    }

    private MoldeRestfulService.SearchLocation getSearchRestfulService() {
        return mCompositionRoot.getSearchLocationRestfulService();
    }

    /**
     * DAO
     */

    private MoldeDatabase mMoldeDatabase;

    private MoldeDatabase getMoldeDatabase() {
        // TODO 싱글턴 유지?
        return Room.databaseBuilder(getActivity(), MoldeDatabase.class, MOLDE_DB).allowMainThreadQueries().build();
    }

    private SearchHistoryDao getSearchHistoryDao() {
        return getMoldeDatabase().getSearchDao();
    }


    /**
     * Controller Helpers
     */

    private FromSchemaToEntity getFromSchemaToEntity() {
        return new FromSchemaToEntity();
    }

    private NetworkHelper getNetworkHelper() {
        return new NetworkHelper(getActivity());
    }

    private SecretRetriever getSecretRetriever() {
        return new SecretRetriever(getActivity());
    }

    public BitmapHelper getBitmapHelper() {
        return new BitmapHelper();
    }

    /**
     * View
     */

    public ActivityScreenNavigator getActivityScreenNavigator() {
        return new ActivityScreenNavigator(getActivity());
    }

    public FragmentScreenNavigator getFragmentScreenNavigator() {
        return new FragmentScreenNavigator(getFragmentFrameHelper());
    }

    public ToastHelper getToastHelper() {
        return new ToastHelper(getActivity());
    }

    private BottomNavigationViewHelper getBottomNavigationViewHelper() {
        return new BottomNavigationViewHelper();
    }

    public DialogManager getDialogManager() {
        return new DialogManager(getFragmentManager());
    }

    public DialogFactory getDialogFactory() {
        return new DialogFactory();
    }

    public ImageLoader getImageLoader() {
        return new ImageLoader(getActivity());
    }

    private FragmentFrameHelper getFragmentFrameHelper() {
        return new FragmentFrameHelper(getActivity(), getFragmentFrameWrapper(), getFragmentManager());
    }

    private FragmentFrameWrapper getFragmentFrameWrapper() {
        return (FragmentFrameWrapper) getActivity();
    }

    public BackPressDispatcher getBackPressDispatcher() {
        return (BackPressDispatcher) getActivity();
    }

    public ViewFactory getViewFactory() {
        return new ViewFactory(
                getLayoutInflater(),
                getImageLoader(),
                getDialogManager(),
                getDialogFactory(),
                getToastHelper(),
                getFragmentManager(),
                getBottomNavigationViewHelper(),
                getBitmapHelper());
    }


    /**
     * UseCase
     */

    public Repository.CardNews getCardNewsUseCase() {
        return new CardNewsUseCase(
                getCardNewsRestfulService(),
                getFromSchemaToEntity(),
                getToastHelper(),
                getNetworkHelper());
    }

    public Repository.Scrap getScrapUseCase() {
        return new ScrapUseCase(
                getScrapRestfulService(),
                getCardNewsRestfulService(),
                getFromSchemaToEntity(),
                getToastHelper(),
                getNetworkHelper());
    }

    public Repository.Feed getFeedUseCase() {
        return new FeedUseCase(
                getFeedRestfulService(),
                getFromSchemaToEntity(),
                getToastHelper(),
                getNetworkHelper());
    }

    public Repository.FeedResult getFeedResultUseCase() {
        return new FeedResultUseCase(
                getFeedResultRestfulService(),
                getToastHelper(),
                getNetworkHelper());
    }

    public Repository.Comment getCommentUseCase() {
        return new CommentUseCase(
                getCommentRestfulService(),
                getCardNewsRestfulService(),
                getFromSchemaToEntity(),
                getToastHelper(),
                getNetworkHelper());
    }

    public Repository.Faq getFaqUseCase() {
        return new FaqUseCase(
                getFaqRestfulService(),
                getFromSchemaToEntity(),
                getToastHelper(),
                getNetworkHelper());
    }

    public Repository.Favorite getFavoriteUseCase() {
        return new FavoriteUseCase(
                getFavoriteRestfulService(),
                getFromSchemaToEntity(),
                getToastHelper(),
                getNetworkHelper());
    }

    public Repository.SearchLocation getSearchLocationUseCase() {
        return new SearchLocationUseCase(
                getFromSchemaToEntity(),
                getToastHelper(),
                getNetworkHelper(),
                getSecretRetriever(),
                getSearchHistoryDao(),
                getSearchRestfulService()
        );
    }

    public Repository.Safehouse getSafehouseUseCase() {
        return new SafehouseUseCase(
                getSafehouseRestfulService(),
                getFromSchemaToEntity(),
                getToastHelper(),
                getNetworkHelper());
    }

}
