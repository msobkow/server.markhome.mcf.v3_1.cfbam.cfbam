// Description: Java 25 Table Object implementation for Chain.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamChainTableObj
	implements ICFBamChainTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamChain.CLASS_CODE;
	protected static final int backingClassCode = ICFBamChain.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamChainObj> members;
	private Map<CFLibDbKeyHash256, ICFBamChainObj> allChain;
	private Map< ICFBamChainByChainTableIdxKey,
		Map<CFLibDbKeyHash256, ICFBamChainObj > > indexByChainTableIdx;
	private Map< ICFBamChainByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamChainObj > > indexByDefSchemaIdx;
	private Map< ICFBamChainByUNameIdxKey,
		ICFBamChainObj > indexByUNameIdx;
	private Map< ICFBamChainByPrevRelIdxKey,
		Map<CFLibDbKeyHash256, ICFBamChainObj > > indexByPrevRelIdx;
	private Map< ICFBamChainByNextRelIdxKey,
		Map<CFLibDbKeyHash256, ICFBamChainObj > > indexByNextRelIdx;
	public static String TABLE_NAME = "Chain";
	public static String TABLE_DBNAME = "chain_def";

	public CFBamChainTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamChainObj>();
		allChain = null;
		indexByChainTableIdx = null;
		indexByDefSchemaIdx = null;
		indexByUNameIdx = null;
		indexByPrevRelIdx = null;
		indexByNextRelIdx = null;
	}

	public CFBamChainTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamChainObj>();
		allChain = null;
		indexByChainTableIdx = null;
		indexByDefSchemaIdx = null;
		indexByUNameIdx = null;
		indexByPrevRelIdx = null;
		indexByNextRelIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamChainTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamChainTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allChain = null;
		indexByChainTableIdx = null;
		indexByDefSchemaIdx = null;
		indexByUNameIdx = null;
		indexByPrevRelIdx = null;
		indexByNextRelIdx = null;
		List<ICFBamChainObj> toForget = new LinkedList<ICFBamChainObj>();
		ICFBamChainObj cur = null;
		Iterator<ICFBamChainObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamChainObj.
	 */
	@Override
	public ICFBamChainObj newInstance() {
		ICFBamChainObj inst = new CFBamChainObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamChainObj.
	 */
	@Override
	public ICFBamChainEditObj newEditInstance( ICFBamChainObj orig ) {
		ICFBamChainEditObj edit = new CFBamChainEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamChainObj realiseChain( ICFBamChainObj Obj ) {
		ICFBamChainObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamChainObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamChainObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByChainTableIdx != null ) {
				ICFBamChainByChainTableIdxKey keyChainTableIdx =
					schema.getCFBamBackingStore().getFactoryChain().newByChainTableIdxKey();
				keyChainTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamChainObj > mapChainTableIdx = indexByChainTableIdx.get( keyChainTableIdx );
				if( mapChainTableIdx != null ) {
					mapChainTableIdx.remove( keepObj.getPKey() );
					if( mapChainTableIdx.size() <= 0 ) {
						indexByChainTableIdx.remove( keyChainTableIdx );
					}
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamChainByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryChain().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamChainObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.remove( keepObj.getPKey() );
					if( mapDefSchemaIdx.size() <= 0 ) {
						indexByDefSchemaIdx.remove( keyDefSchemaIdx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamChainByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryChain().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByPrevRelIdx != null ) {
				ICFBamChainByPrevRelIdxKey keyPrevRelIdx =
					schema.getCFBamBackingStore().getFactoryChain().newByPrevRelIdxKey();
				keyPrevRelIdx.setRequiredPrevRelationId( keepObj.getRequiredPrevRelationId() );
				Map<CFLibDbKeyHash256, ICFBamChainObj > mapPrevRelIdx = indexByPrevRelIdx.get( keyPrevRelIdx );
				if( mapPrevRelIdx != null ) {
					mapPrevRelIdx.remove( keepObj.getPKey() );
					if( mapPrevRelIdx.size() <= 0 ) {
						indexByPrevRelIdx.remove( keyPrevRelIdx );
					}
				}
			}

			if( indexByNextRelIdx != null ) {
				ICFBamChainByNextRelIdxKey keyNextRelIdx =
					schema.getCFBamBackingStore().getFactoryChain().newByNextRelIdxKey();
				keyNextRelIdx.setRequiredNextRelationId( keepObj.getRequiredNextRelationId() );
				Map<CFLibDbKeyHash256, ICFBamChainObj > mapNextRelIdx = indexByNextRelIdx.get( keyNextRelIdx );
				if( mapNextRelIdx != null ) {
					mapNextRelIdx.remove( keepObj.getPKey() );
					if( mapNextRelIdx.size() <= 0 ) {
						indexByNextRelIdx.remove( keyNextRelIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByChainTableIdx != null ) {
				ICFBamChainByChainTableIdxKey keyChainTableIdx =
					schema.getCFBamBackingStore().getFactoryChain().newByChainTableIdxKey();
				keyChainTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamChainObj > mapChainTableIdx = indexByChainTableIdx.get( keyChainTableIdx );
				if( mapChainTableIdx != null ) {
					mapChainTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamChainByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryChain().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamChainObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamChainByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryChain().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByPrevRelIdx != null ) {
				ICFBamChainByPrevRelIdxKey keyPrevRelIdx =
					schema.getCFBamBackingStore().getFactoryChain().newByPrevRelIdxKey();
				keyPrevRelIdx.setRequiredPrevRelationId( keepObj.getRequiredPrevRelationId() );
				Map<CFLibDbKeyHash256, ICFBamChainObj > mapPrevRelIdx = indexByPrevRelIdx.get( keyPrevRelIdx );
				if( mapPrevRelIdx != null ) {
					mapPrevRelIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextRelIdx != null ) {
				ICFBamChainByNextRelIdxKey keyNextRelIdx =
					schema.getCFBamBackingStore().getFactoryChain().newByNextRelIdxKey();
				keyNextRelIdx.setRequiredNextRelationId( keepObj.getRequiredNextRelationId() );
				Map<CFLibDbKeyHash256, ICFBamChainObj > mapNextRelIdx = indexByNextRelIdx.get( keyNextRelIdx );
				if( mapNextRelIdx != null ) {
					mapNextRelIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allChain != null ) {
				allChain.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allChain != null ) {
				allChain.put( keepObj.getPKey(), keepObj );
			}

			if( indexByChainTableIdx != null ) {
				ICFBamChainByChainTableIdxKey keyChainTableIdx =
					schema.getCFBamBackingStore().getFactoryChain().newByChainTableIdxKey();
				keyChainTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamChainObj > mapChainTableIdx = indexByChainTableIdx.get( keyChainTableIdx );
				if( mapChainTableIdx != null ) {
					mapChainTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamChainByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryChain().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamChainObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamChainByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryChain().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByPrevRelIdx != null ) {
				ICFBamChainByPrevRelIdxKey keyPrevRelIdx =
					schema.getCFBamBackingStore().getFactoryChain().newByPrevRelIdxKey();
				keyPrevRelIdx.setRequiredPrevRelationId( keepObj.getRequiredPrevRelationId() );
				Map<CFLibDbKeyHash256, ICFBamChainObj > mapPrevRelIdx = indexByPrevRelIdx.get( keyPrevRelIdx );
				if( mapPrevRelIdx != null ) {
					mapPrevRelIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextRelIdx != null ) {
				ICFBamChainByNextRelIdxKey keyNextRelIdx =
					schema.getCFBamBackingStore().getFactoryChain().newByNextRelIdxKey();
				keyNextRelIdx.setRequiredNextRelationId( keepObj.getRequiredNextRelationId() );
				Map<CFLibDbKeyHash256, ICFBamChainObj > mapNextRelIdx = indexByNextRelIdx.get( keyNextRelIdx );
				if( mapNextRelIdx != null ) {
					mapNextRelIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamChainObj createChain( ICFBamChainObj Obj ) {
		ICFBamChainObj obj = Obj;
		ICFBamChain rec = obj.getChainRec();
		schema.getCFBamBackingStore().getTableChain().createChain(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamChainObj readChain( CFLibDbKeyHash256 pkey ) {
		return( readChain( pkey, false ) );
	}

	@Override
	public ICFBamChainObj readChain( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamChainObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamChain readRec = schema.getCFBamBackingStore().getTableChain().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getChainTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamChainObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamChainObj readCachedChain( CFLibDbKeyHash256 pkey ) {
		ICFBamChainObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeChain( ICFBamChainObj obj )
	{
		final String S_ProcName = "CFBamChainTableObj.reallyDeepDisposeChain() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamChainObj existing = readCachedChain( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamChainByChainTableIdxKey keyChainTableIdx = schema.getCFBamBackingStore().getFactoryChain().newByChainTableIdxKey();
		keyChainTableIdx.setRequiredTableId( existing.getRequiredTableId() );

		ICFBamChainByDefSchemaIdxKey keyDefSchemaIdx = schema.getCFBamBackingStore().getFactoryChain().newByDefSchemaIdxKey();
		keyDefSchemaIdx.setOptionalDefSchemaId( existing.getOptionalDefSchemaId() );

		ICFBamChainByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryChain().newByUNameIdxKey();
		keyUNameIdx.setRequiredTableId( existing.getRequiredTableId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );

		ICFBamChainByPrevRelIdxKey keyPrevRelIdx = schema.getCFBamBackingStore().getFactoryChain().newByPrevRelIdxKey();
		keyPrevRelIdx.setRequiredPrevRelationId( existing.getRequiredPrevRelationId() );

		ICFBamChainByNextRelIdxKey keyNextRelIdx = schema.getCFBamBackingStore().getFactoryChain().newByNextRelIdxKey();
		keyNextRelIdx.setRequiredNextRelationId( existing.getRequiredNextRelationId() );



		if( indexByChainTableIdx != null ) {
			if( indexByChainTableIdx.containsKey( keyChainTableIdx ) ) {
				indexByChainTableIdx.get( keyChainTableIdx ).remove( pkey );
				if( indexByChainTableIdx.get( keyChainTableIdx ).size() <= 0 ) {
					indexByChainTableIdx.remove( keyChainTableIdx );
				}
			}
		}

		if( indexByDefSchemaIdx != null ) {
			if( indexByDefSchemaIdx.containsKey( keyDefSchemaIdx ) ) {
				indexByDefSchemaIdx.get( keyDefSchemaIdx ).remove( pkey );
				if( indexByDefSchemaIdx.get( keyDefSchemaIdx ).size() <= 0 ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}

		if( indexByPrevRelIdx != null ) {
			if( indexByPrevRelIdx.containsKey( keyPrevRelIdx ) ) {
				indexByPrevRelIdx.get( keyPrevRelIdx ).remove( pkey );
				if( indexByPrevRelIdx.get( keyPrevRelIdx ).size() <= 0 ) {
					indexByPrevRelIdx.remove( keyPrevRelIdx );
				}
			}
		}

		if( indexByNextRelIdx != null ) {
			if( indexByNextRelIdx.containsKey( keyNextRelIdx ) ) {
				indexByNextRelIdx.get( keyNextRelIdx ).remove( pkey );
				if( indexByNextRelIdx.get( keyNextRelIdx ).size() <= 0 ) {
					indexByNextRelIdx.remove( keyNextRelIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeChain( CFLibDbKeyHash256 pkey ) {
		ICFBamChainObj obj = readCachedChain( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamChainObj lockChain( CFLibDbKeyHash256 pkey ) {
		ICFBamChainObj locked = null;
		ICFBamChain lockRec = schema.getCFBamBackingStore().getTableChain().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getChainTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamChainObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockChain", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamChainObj> readAllChain() {
		return( readAllChain( false ) );
	}

	@Override
	public List<ICFBamChainObj> readAllChain( boolean forceRead ) {
		final String S_ProcName = "readAllChain";
		if( ( allChain == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamChainObj> map = new HashMap<CFLibDbKeyHash256,ICFBamChainObj>();
			allChain = map;
			ICFBamChain[] recList = schema.getCFBamBackingStore().getTableChain().readAllDerived( null );
			ICFBamChain rec;
			ICFBamChainObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamChainObj realised = (ICFBamChainObj)obj.realise();
			}
		}
		int len = allChain.size();
		ICFBamChainObj arr[] = new ICFBamChainObj[len];
		Iterator<ICFBamChainObj> valIter = allChain.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamChainObj> arrayList = new ArrayList<ICFBamChainObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamChainObj> cmp = new Comparator<ICFBamChainObj>() {
			@Override
			public int compare( ICFBamChainObj lhs, ICFBamChainObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamChainObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamChainObj> readCachedAllChain() {
		final String S_ProcName = "readCachedAllChain";
		ArrayList<ICFBamChainObj> arrayList = new ArrayList<ICFBamChainObj>();
		if( allChain != null ) {
			int len = allChain.size();
			ICFBamChainObj arr[] = new ICFBamChainObj[len];
			Iterator<ICFBamChainObj> valIter = allChain.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamChainObj> cmp = new Comparator<ICFBamChainObj>() {
			public int compare( ICFBamChainObj lhs, ICFBamChainObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamChainObj readChainByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readChainByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamChainObj readChainByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamChainObj obj = readChain( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamChainObj> readChainByChainTableIdx( CFLibDbKeyHash256 TableId )
	{
		return( readChainByChainTableIdx( TableId,
			false ) );
	}

	@Override
	public List<ICFBamChainObj> readChainByChainTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead )
	{
		final String S_ProcName = "readChainByChainTableIdx";
		ICFBamChainByChainTableIdxKey key = schema.getCFBamBackingStore().getFactoryChain().newByChainTableIdxKey();
		key.setRequiredTableId( TableId );
		Map<CFLibDbKeyHash256, ICFBamChainObj> dict;
		if( indexByChainTableIdx == null ) {
			indexByChainTableIdx = new HashMap< ICFBamChainByChainTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamChainObj > >();
		}
		if( ( ! forceRead ) && indexByChainTableIdx.containsKey( key ) ) {
			dict = indexByChainTableIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamChainObj>();
			ICFBamChainObj obj;
			ICFBamChain[] recList = schema.getCFBamBackingStore().getTableChain().readDerivedByChainTableIdx( null,
				TableId );
			ICFBamChain rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getChainTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamChainObj realised = (ICFBamChainObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByChainTableIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamChainObj arr[] = new ICFBamChainObj[len];
		Iterator<ICFBamChainObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamChainObj> arrayList = new ArrayList<ICFBamChainObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamChainObj> cmp = new Comparator<ICFBamChainObj>() {
			@Override
			public int compare( ICFBamChainObj lhs, ICFBamChainObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamChainObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamChainObj> readChainByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readChainByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamChainObj> readChainByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readChainByDefSchemaIdx";
		ICFBamChainByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryChain().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamChainObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamChainByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamChainObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamChainObj>();
			ICFBamChainObj obj;
			ICFBamChain[] recList = schema.getCFBamBackingStore().getTableChain().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamChain rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getChainTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamChainObj realised = (ICFBamChainObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamChainObj arr[] = new ICFBamChainObj[len];
		Iterator<ICFBamChainObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamChainObj> arrayList = new ArrayList<ICFBamChainObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamChainObj> cmp = new Comparator<ICFBamChainObj>() {
			@Override
			public int compare( ICFBamChainObj lhs, ICFBamChainObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamChainObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamChainObj readChainByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		return( readChainByUNameIdx( TableId,
			Name,
			false ) );
	}

	@Override
	public ICFBamChainObj readChainByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamChainByUNameIdxKey,
				ICFBamChainObj >();
		}
		ICFBamChainByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryChain().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamChainObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamChain rec = schema.getCFBamBackingStore().getTableChain().readDerivedByUNameIdx( null,
				TableId,
				Name );
			if( rec != null ) {
				obj = schema.getChainTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamChainObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamChainObj> readChainByPrevRelIdx( CFLibDbKeyHash256 PrevRelationId )
	{
		return( readChainByPrevRelIdx( PrevRelationId,
			false ) );
	}

	@Override
	public List<ICFBamChainObj> readChainByPrevRelIdx( CFLibDbKeyHash256 PrevRelationId,
		boolean forceRead )
	{
		final String S_ProcName = "readChainByPrevRelIdx";
		ICFBamChainByPrevRelIdxKey key = schema.getCFBamBackingStore().getFactoryChain().newByPrevRelIdxKey();
		key.setRequiredPrevRelationId( PrevRelationId );
		Map<CFLibDbKeyHash256, ICFBamChainObj> dict;
		if( indexByPrevRelIdx == null ) {
			indexByPrevRelIdx = new HashMap< ICFBamChainByPrevRelIdxKey,
				Map< CFLibDbKeyHash256, ICFBamChainObj > >();
		}
		if( ( ! forceRead ) && indexByPrevRelIdx.containsKey( key ) ) {
			dict = indexByPrevRelIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamChainObj>();
			ICFBamChainObj obj;
			ICFBamChain[] recList = schema.getCFBamBackingStore().getTableChain().readDerivedByPrevRelIdx( null,
				PrevRelationId );
			ICFBamChain rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getChainTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamChainObj realised = (ICFBamChainObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPrevRelIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamChainObj arr[] = new ICFBamChainObj[len];
		Iterator<ICFBamChainObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamChainObj> arrayList = new ArrayList<ICFBamChainObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamChainObj> cmp = new Comparator<ICFBamChainObj>() {
			@Override
			public int compare( ICFBamChainObj lhs, ICFBamChainObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamChainObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamChainObj> readChainByNextRelIdx( CFLibDbKeyHash256 NextRelationId )
	{
		return( readChainByNextRelIdx( NextRelationId,
			false ) );
	}

	@Override
	public List<ICFBamChainObj> readChainByNextRelIdx( CFLibDbKeyHash256 NextRelationId,
		boolean forceRead )
	{
		final String S_ProcName = "readChainByNextRelIdx";
		ICFBamChainByNextRelIdxKey key = schema.getCFBamBackingStore().getFactoryChain().newByNextRelIdxKey();
		key.setRequiredNextRelationId( NextRelationId );
		Map<CFLibDbKeyHash256, ICFBamChainObj> dict;
		if( indexByNextRelIdx == null ) {
			indexByNextRelIdx = new HashMap< ICFBamChainByNextRelIdxKey,
				Map< CFLibDbKeyHash256, ICFBamChainObj > >();
		}
		if( ( ! forceRead ) && indexByNextRelIdx.containsKey( key ) ) {
			dict = indexByNextRelIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamChainObj>();
			ICFBamChainObj obj;
			ICFBamChain[] recList = schema.getCFBamBackingStore().getTableChain().readDerivedByNextRelIdx( null,
				NextRelationId );
			ICFBamChain rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getChainTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamChainObj realised = (ICFBamChainObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNextRelIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamChainObj arr[] = new ICFBamChainObj[len];
		Iterator<ICFBamChainObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamChainObj> arrayList = new ArrayList<ICFBamChainObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamChainObj> cmp = new Comparator<ICFBamChainObj>() {
			@Override
			public int compare( ICFBamChainObj lhs, ICFBamChainObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamChainObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamChainObj readCachedChainByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamChainObj obj = null;
		obj = readCachedChain( Id );
		return( obj );
	}

	@Override
	public List<ICFBamChainObj> readCachedChainByChainTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "readCachedChainByChainTableIdx";
		ICFBamChainByChainTableIdxKey key = schema.getCFBamBackingStore().getFactoryChain().newByChainTableIdxKey();
		key.setRequiredTableId( TableId );
		ArrayList<ICFBamChainObj> arrayList = new ArrayList<ICFBamChainObj>();
		if( indexByChainTableIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamChainObj> dict;
			if( indexByChainTableIdx.containsKey( key ) ) {
				dict = indexByChainTableIdx.get( key );
				int len = dict.size();
				ICFBamChainObj arr[] = new ICFBamChainObj[len];
				Iterator<ICFBamChainObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamChainObj obj;
			Iterator<ICFBamChainObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamChainObj> cmp = new Comparator<ICFBamChainObj>() {
			@Override
			public int compare( ICFBamChainObj lhs, ICFBamChainObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamChainObj> readCachedChainByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedChainByDefSchemaIdx";
		ICFBamChainByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryChain().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamChainObj> arrayList = new ArrayList<ICFBamChainObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamChainObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamChainObj arr[] = new ICFBamChainObj[len];
				Iterator<ICFBamChainObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamChainObj obj;
			Iterator<ICFBamChainObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamChainObj> cmp = new Comparator<ICFBamChainObj>() {
			@Override
			public int compare( ICFBamChainObj lhs, ICFBamChainObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamChainObj readCachedChainByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamChainObj obj = null;
		ICFBamChainByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryChain().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamChainObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamChainObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamChainObj> readCachedChainByPrevRelIdx( CFLibDbKeyHash256 PrevRelationId )
	{
		final String S_ProcName = "readCachedChainByPrevRelIdx";
		ICFBamChainByPrevRelIdxKey key = schema.getCFBamBackingStore().getFactoryChain().newByPrevRelIdxKey();
		key.setRequiredPrevRelationId( PrevRelationId );
		ArrayList<ICFBamChainObj> arrayList = new ArrayList<ICFBamChainObj>();
		if( indexByPrevRelIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamChainObj> dict;
			if( indexByPrevRelIdx.containsKey( key ) ) {
				dict = indexByPrevRelIdx.get( key );
				int len = dict.size();
				ICFBamChainObj arr[] = new ICFBamChainObj[len];
				Iterator<ICFBamChainObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamChainObj obj;
			Iterator<ICFBamChainObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamChainObj> cmp = new Comparator<ICFBamChainObj>() {
			@Override
			public int compare( ICFBamChainObj lhs, ICFBamChainObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamChainObj> readCachedChainByNextRelIdx( CFLibDbKeyHash256 NextRelationId )
	{
		final String S_ProcName = "readCachedChainByNextRelIdx";
		ICFBamChainByNextRelIdxKey key = schema.getCFBamBackingStore().getFactoryChain().newByNextRelIdxKey();
		key.setRequiredNextRelationId( NextRelationId );
		ArrayList<ICFBamChainObj> arrayList = new ArrayList<ICFBamChainObj>();
		if( indexByNextRelIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamChainObj> dict;
			if( indexByNextRelIdx.containsKey( key ) ) {
				dict = indexByNextRelIdx.get( key );
				int len = dict.size();
				ICFBamChainObj arr[] = new ICFBamChainObj[len];
				Iterator<ICFBamChainObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamChainObj obj;
			Iterator<ICFBamChainObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamChainObj> cmp = new Comparator<ICFBamChainObj>() {
			@Override
			public int compare( ICFBamChainObj lhs, ICFBamChainObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeChainByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamChainObj obj = readCachedChainByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeChainByChainTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "deepDisposeChainByChainTableIdx";
		ICFBamChainObj obj;
		List<ICFBamChainObj> arrayList = readCachedChainByChainTableIdx( TableId );
		if( arrayList != null )  {
			Iterator<ICFBamChainObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeChainByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeChainByDefSchemaIdx";
		ICFBamChainObj obj;
		List<ICFBamChainObj> arrayList = readCachedChainByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamChainObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeChainByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamChainObj obj = readCachedChainByUNameIdx( TableId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeChainByPrevRelIdx( CFLibDbKeyHash256 PrevRelationId )
	{
		final String S_ProcName = "deepDisposeChainByPrevRelIdx";
		ICFBamChainObj obj;
		List<ICFBamChainObj> arrayList = readCachedChainByPrevRelIdx( PrevRelationId );
		if( arrayList != null )  {
			Iterator<ICFBamChainObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeChainByNextRelIdx( CFLibDbKeyHash256 NextRelationId )
	{
		final String S_ProcName = "deepDisposeChainByNextRelIdx";
		ICFBamChainObj obj;
		List<ICFBamChainObj> arrayList = readCachedChainByNextRelIdx( NextRelationId );
		if( arrayList != null )  {
			Iterator<ICFBamChainObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamChainObj updateChain( ICFBamChainObj Obj ) {
		ICFBamChainObj obj = Obj;
		schema.getCFBamBackingStore().getTableChain().updateChain( null,
			Obj.getChainRec() );
		obj = (ICFBamChainObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteChain( ICFBamChainObj Obj ) {
		ICFBamChainObj obj = Obj;
		schema.getCFBamBackingStore().getTableChain().deleteChain( null,
			obj.getChainRec() );
		Obj.forget();
	}

	@Override
	public void deleteChainByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamChainObj obj = readChain(Id);
		if( obj != null ) {
			ICFBamChainEditObj editObj = (ICFBamChainEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamChainEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeChainByIdIdx( Id );
	}

	@Override
	public void deleteChainByChainTableIdx( CFLibDbKeyHash256 TableId )
	{
		ICFBamChainByChainTableIdxKey key = schema.getCFBamBackingStore().getFactoryChain().newByChainTableIdxKey();
		key.setRequiredTableId( TableId );
		if( indexByChainTableIdx == null ) {
			indexByChainTableIdx = new HashMap< ICFBamChainByChainTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamChainObj > >();
		}
		if( indexByChainTableIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamChainObj> dict = indexByChainTableIdx.get( key );
			schema.getCFBamBackingStore().getTableChain().deleteChainByChainTableIdx( null,
				TableId );
			Iterator<ICFBamChainObj> iter = dict.values().iterator();
			ICFBamChainObj obj;
			List<ICFBamChainObj> toForget = new LinkedList<ICFBamChainObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByChainTableIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableChain().deleteChainByChainTableIdx( null,
				TableId );
		}
		deepDisposeChainByChainTableIdx( TableId );
	}

	@Override
	public void deleteChainByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamChainByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryChain().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamChainByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamChainObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamChainObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableChain().deleteChainByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamChainObj> iter = dict.values().iterator();
			ICFBamChainObj obj;
			List<ICFBamChainObj> toForget = new LinkedList<ICFBamChainObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableChain().deleteChainByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeChainByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteChainByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamChainByUNameIdxKey,
				ICFBamChainObj >();
		}
		ICFBamChainByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryChain().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamChainObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableChain().deleteChainByUNameIdx( null,
				TableId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableChain().deleteChainByUNameIdx( null,
				TableId,
				Name );
		}
		deepDisposeChainByUNameIdx( TableId,
				Name );
	}

	@Override
	public void deleteChainByPrevRelIdx( CFLibDbKeyHash256 PrevRelationId )
	{
		ICFBamChainByPrevRelIdxKey key = schema.getCFBamBackingStore().getFactoryChain().newByPrevRelIdxKey();
		key.setRequiredPrevRelationId( PrevRelationId );
		if( indexByPrevRelIdx == null ) {
			indexByPrevRelIdx = new HashMap< ICFBamChainByPrevRelIdxKey,
				Map< CFLibDbKeyHash256, ICFBamChainObj > >();
		}
		if( indexByPrevRelIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamChainObj> dict = indexByPrevRelIdx.get( key );
			schema.getCFBamBackingStore().getTableChain().deleteChainByPrevRelIdx( null,
				PrevRelationId );
			Iterator<ICFBamChainObj> iter = dict.values().iterator();
			ICFBamChainObj obj;
			List<ICFBamChainObj> toForget = new LinkedList<ICFBamChainObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByPrevRelIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableChain().deleteChainByPrevRelIdx( null,
				PrevRelationId );
		}
		deepDisposeChainByPrevRelIdx( PrevRelationId );
	}

	@Override
	public void deleteChainByNextRelIdx( CFLibDbKeyHash256 NextRelationId )
	{
		ICFBamChainByNextRelIdxKey key = schema.getCFBamBackingStore().getFactoryChain().newByNextRelIdxKey();
		key.setRequiredNextRelationId( NextRelationId );
		if( indexByNextRelIdx == null ) {
			indexByNextRelIdx = new HashMap< ICFBamChainByNextRelIdxKey,
				Map< CFLibDbKeyHash256, ICFBamChainObj > >();
		}
		if( indexByNextRelIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamChainObj> dict = indexByNextRelIdx.get( key );
			schema.getCFBamBackingStore().getTableChain().deleteChainByNextRelIdx( null,
				NextRelationId );
			Iterator<ICFBamChainObj> iter = dict.values().iterator();
			ICFBamChainObj obj;
			List<ICFBamChainObj> toForget = new LinkedList<ICFBamChainObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByNextRelIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableChain().deleteChainByNextRelIdx( null,
				NextRelationId );
		}
		deepDisposeChainByNextRelIdx( NextRelationId );
	}
}