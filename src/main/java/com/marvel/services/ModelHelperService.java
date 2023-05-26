package com.marvel.services;

import com.marvel.api.v1.model.QueryCharacterModel;
import com.marvel.api.v1.model.QueryComicModel;
import com.marvel.exceptions.NotValidParametersException;
import org.springframework.stereotype.Service;

@Service
public class ModelHelperService {

    private final String ORDER_BY_NAME = "name";
    private final String ORDER_BY_TITLE = "title";
    private final String DEFAULT_MIN_DATE = "1900-01-01 00:00:00";
    private final String DEFAULT_MAX_DATE = "2900-01-01 00:00:00";
    private final Integer DEFAULT_PAGE_SIZE = 15;
    private final Integer DEFAULT_NUMBER_PAGE = 0;
    public static final Long MINUS_ONE = -1L;

    public QueryCharacterModel setParametersIntoQueryCharacterModel(String comicId,
                                                                    String numberPage,
                                                                    String pageSize,
                                                                    String orderBy,
                                                                    String modifiedStart,
                                                                    String modifiedEnd) throws NotValidParametersException {

        QueryCharacterModel model = new QueryCharacterModel();

        try {
            if (comicId != null)
                model.setComicId(Long.valueOf(comicId));
            else
                model.setComicId(MINUS_ONE);

            if (numberPage != null)
                model.setNumberPage(Integer.valueOf(numberPage));
            else
                model.setNumberPage(DEFAULT_NUMBER_PAGE);

            if (pageSize != null)
                model.setPageSize(Integer.valueOf(pageSize));
            else
                model.setPageSize(DEFAULT_PAGE_SIZE);

            if (orderBy != null)
                model.setOrderBy(orderBy);
            else
                model.setOrderBy(ORDER_BY_NAME);

            if (modifiedStart != null)
                model.setModifiedDateStart(modifiedStart);
            else
                model.setModifiedDateStart(DEFAULT_MIN_DATE);

            if (modifiedEnd != null)
                model.setModifiedDateEnd(modifiedEnd);
            else
                model.setModifiedDateEnd(DEFAULT_MAX_DATE);
        } catch (Exception e) {
            throw new NotValidParametersException("invalid url parameters");
        }

        return model;
    }

    public QueryComicModel setParametersIntoQueryComicModel(String numberPage,
                                                            String pageSize,
                                                            String title,
                                                            String dateStart,
                                                            String dateEnd,
                                                            String orderBy) throws NotValidParametersException {

        QueryComicModel model = new QueryComicModel();

        try {
            if (numberPage != null)
                model.setNumberPage(Integer.valueOf(numberPage));
            else
                model.setNumberPage(DEFAULT_NUMBER_PAGE);

            if (pageSize != null)
                model.setPageSize(Integer.valueOf(pageSize));
            else
                model.setPageSize(DEFAULT_PAGE_SIZE);

            if (dateStart != null)
                model.setDateStart(dateStart);
            else
                model.setDateStart(DEFAULT_MIN_DATE);

            if (dateEnd != null)
                model.setDateEnd(dateEnd);
            else
                model.setDateEnd(DEFAULT_MAX_DATE);

            if (orderBy != null)
                model.setOrderBy(orderBy);
            else
                model.setOrderBy(ORDER_BY_TITLE);

            model.setTitle(title);

        } catch (Exception e) {
            throw new NotValidParametersException("invalid url parameters");
        }

        return model;
    }
}
